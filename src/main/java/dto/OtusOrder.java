
package dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@AllArgsConstructor
@Builder
@JsonSerialize
public class OtusOrder {

    private Long id;
    private Long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private Boolean complete;
}
