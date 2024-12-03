'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const { strict } = require('yeoman-assert');

// for TypeScript
// type Field = {
//   fieldName: string;
//   javaType: string;
//   multiple: Boolean;
//   dbType: strict;
//   dbTypeSize: int;
//   id: Boolean;
//   required: Boolean;
// }

// type Metadata = {
//   packageName: string;
//   className: string;
//   fields: Field[];
// }

// type MetadataList = Metadata[];

const JEG_META_FPATH = 'metadata/jeg-metadata.json';
const LAYERS = ['Repository', 'Service', 'Controller'];

module.exports = class extends Generator {
  initializing() {
    try {
      this.metadataList = this.fs.readJSON(this.templatePath(JEG_META_FPATH));

      if (!this.metadataList || this.metadataList.length === 0) {
        throw new Error(`Meta data list on ${JEG_META_FPATH} is empty.`);
      }
    } catch (error) {
      this.log(`Failed to read ${JEG_META_FPATH}.`, error);
    }
  }

  writing() {
    const outputJavaFile = (packageName, entityName, layer) => {
      const destPkgPath = `src/main/java/${packageName.replaceAll('.', '/')}`;
      const archBasePkg = this.config.get('archPkg');
      const ejsData = {
        pkgName: packageName,
        entityName: entityName,
        archBasePkg: archBasePkg
      };

      this.fs.copyTpl(
        this.templatePath(`java/${layer}.java`),
        this.destinationPath(`${destPkgPath}/${entityName}${layer}.java`),
        ejsData
      );
    }

    const pickEntityName = (entityClassName) => {
      return entityClassName.replace('Entity', '');
    }

    this.metadataList.forEach(metadata => {
      const { packageName, className, fields } = metadata;

      LAYERS.forEach(layer => {
        const entityName = pickEntityName(className);
        outputJavaFile(packageName, entityName, layer);
      });
    });
  }
  
  end() {
    this.log('');
  }
};

