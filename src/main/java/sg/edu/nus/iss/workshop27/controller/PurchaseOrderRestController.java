package sg.edu.nus.iss.workshop27.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.workshop27.exception.OrderTooLargeException;
import sg.edu.nus.iss.workshop27.models.PurchaseOrder;
import sg.edu.nus.iss.workshop27.service.PurchaseOrderService;

@RestController
@RequestMapping(path="/api/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class PurchaseOrderRestController {
    
    @Autowired
    private PurchaseOrderService poSvc;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody String json){
        PurchaseOrder order = null;
        JsonObject resp;
        try {
            order = PurchaseOrder.create(json);
        } catch (Exception e) {
            e.printStackTrace();
            resp = Json.createObjectBuilder()
                .add("error", e.getMessage())
                .build();
            return ResponseEntity.badRequest().body(resp.toString());
        }
        Integer orderId;
        try {
            orderId = poSvc.createPurchaseOrder(order);
            resp = Json.createObjectBuilder()
                .add("orderId", orderId)
                .build();
        } catch (OrderTooLargeException e) {
            e.printStackTrace();
            resp = Json.createObjectBuilder()
                .add("error", e.getMessage())
                .build();
            return ResponseEntity.badRequest().body(resp.toString());
        }
        
        return ResponseEntity.ok(resp.toString());
    }
}
