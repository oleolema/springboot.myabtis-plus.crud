package #{#mainPackage}.bean;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import lombok.experimental.Accessors;
#{#imports}

@Slf4j
@Data
@Accessors(chain = true)
@FieldNameConstants
public class #{#className} implements Serializable{
    private static final long serialVersionUID = 1L;

#{#fields}
}