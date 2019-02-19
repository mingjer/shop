
package net.realme.scm.wms.domain.delhivery;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author 91000044
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DelhiveryResponse {

    private boolean acknowledged;
    private String message;

    private String requestId;

    public void setAcknowledged(boolean acknowledged) {
         this.acknowledged = acknowledged;
     }
     public boolean getAcknowledged() {
         return acknowledged;
     }

    public void setMessage(String message) {
         this.message = message;
     }
     public String getMessage() {
         return message;
     }

    public void setRequestId(String requestId) {
         this.requestId = requestId;
     }
     public String getRequestId() {
         return requestId;
     }

    @Override
    public String toString() {
        return "DelhiveryResponse{" +
                "acknowledged=" + acknowledged +
                ", message='" + message + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}