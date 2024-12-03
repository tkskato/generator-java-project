package <%= pkgName %>;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class <%= entityName %>Repository extends JpaRepository<<%= entityName %>Entity, Integer> {}
