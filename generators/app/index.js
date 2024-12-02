'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');


// IssueRepository.java
// IssueService.java
// IssueController.java
// javaファイル内はコンパイル可能なimport、@RequiredArgsConstructor、private finalを生成

module.exports = class extends Generator {
  
  prompting() {
    this.log(
      yosay(
        `Welcome to the awe-inspiring ${chalk.red('generator-java-project')} generator!`
      )
    );

    const prompts = [
      {
        type: 'input',
        name: 'domainName',
        message: 'ドメイン名を入力してください：',
        default: 'Hoge'
      }
    ];

    return this.prompt(prompts).then(props => {
      this.props = props;
    });
  }

  writing() {
    const { domainName, packageName } = this.props;

    const domainLower = domainName.toLowerCase();

    const basePkg = 'dev.aulait.svqk';
    const archBasePkg = `${basePkg}.arch`;
    const domainBasePkg = `${basePkg}.domain`;
    const interfacesBasePkg = `${basePkg}.interfaces`;
    
    const targetPkgDomain = `${domainBasePkg}.${domainLower}`
    const targetPkgInterfaces = `${interfacesBasePkg}.${domainLower}`

    let pkgPath = `src/main/java/${targetPkgDomain.replaceAll('.', '/')}`;
    let layer = 'Repository';
    let ejsData = {
      domainName: domainName,
      packageName: targetPkgDomain,
      targetPkgDomain: targetPkgDomain,
      archBasePkg: archBasePkg,
    };

    this.fs.copyTpl(
      this.templatePath(`${layer}.java`),
      this.destinationPath(`${pkgPath}/${domainName}${layer}.java`),
      ejsData
    );
    layer = 'Service';
    this.fs.copyTpl(
      this.templatePath(`${layer}.java`),
      this.destinationPath(`${pkgPath}/${domainName}${layer}.java`),
      ejsData
    );

    pkgPath = `src/main/java/${targetPkgInterfaces.replaceAll('.', '/')}`;
    layer = 'Controller';
    ejsData = {
      domainName: domainName,
      packageName: targetPkgInterfaces,
      targetPkgDomain: targetPkgDomain,
      archBasePkg: archBasePkg,
    };

    this.fs.copyTpl(
      this.templatePath(`${layer}.java`),
      this.destinationPath(`${pkgPath}/${domainName}${layer}.java`),
      ejsData
    );
  }
  
  end() {
    this.log('');
    this.log('生成しました！');
    this.log('');
  }
};
