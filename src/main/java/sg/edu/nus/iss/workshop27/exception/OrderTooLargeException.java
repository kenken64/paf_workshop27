package sg.edu.nus.iss.workshop27.exception;

import sg.edu.nus.iss.workshop27.models.PurchaseOrder;

public class OrderTooLargeException extends Exception{
    private PurchaseOrder po;

    public PurchaseOrder getPo() {
        return po;
    }
    public void setPo(PurchaseOrder po) {
        this.po = po;
    }
    public OrderTooLargeException() {super();}
    public OrderTooLargeException(String msg){
        super(msg);
    }

     
    
}
