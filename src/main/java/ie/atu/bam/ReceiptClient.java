package ie.atu.bam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="receipt-service", url = "http://localhost:8082/receipt")
public interface ReceiptClient {

    @PostMapping("/createRec/{IBAN}/{uID}/{inout}/{num}")
    void createReceipt(@PathVariable int IBAN, @PathVariable Long uID, @PathVariable String inout, @PathVariable float num);

    @GetMapping("/getRec/{uID}")
    List<Object> getRecs(@PathVariable Long uID);

    @PostMapping("/transferRec/{IBAN1}/{uID1}/{IBAN2}/{uID2}/{num}")
    void transferRec(@PathVariable int IBAN1,@PathVariable Long uID1, @PathVariable int IBAN2,@PathVariable Long uID2,@PathVariable float num);
}
