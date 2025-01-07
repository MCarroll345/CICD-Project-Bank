package ie.atu.bam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="receipt-service", url = "http://localhost:8082/receipt")
public interface ReceiptClient {

    @PostMapping("/createRec/{IBAN}/{inout}/{num}")
    void createReceipt(@PathVariable int IBAN, @PathVariable String inout, @PathVariable float num);

    @GetMapping("/getRec/{IBAN}")
    List<Object> getRecs(@PathVariable int IBAN);

    @PostMapping("/transferRec/{IBAN1}/{IBAN2}/{num}")
    void transferRec(@PathVariable int IBAN1, @PathVariable int IBAN2,@PathVariable float num);
}
