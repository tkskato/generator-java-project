package <%= packageName %>;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class <%= domainName %>Repository extends JpaRepository<<%= domainName %>Entity, Integer> {}
