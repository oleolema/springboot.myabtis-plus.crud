package #{#mainPackage}.bean;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.extern.slf4j.Slf4j;
import lombok.experimental.Accessors;
#{#imports}

@Slf4j
@Data
@Accessors(chain = true)
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class #{#className} implements Serializable{
    private static final long serialVersionUID = 1L;

#{#fields}
}