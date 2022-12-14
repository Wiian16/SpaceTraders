import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Loan {
    private Date due;
    private String id, status, type;
    private double repaymentAmount;

    public Loan(Date due, String id, double repaymentAmount, String status, String type){
        this.due = (Date) due.clone();
        this.id = id;
        this.repaymentAmount = repaymentAmount;
        this.status = status;
        this.type = type;
    }

    public Loan(HashMap<String, String> map) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss.SSS'Z'");

        due = df.parse(map.get("due"));
        id = map.get("id");
        repaymentAmount = Double.parseDouble(map.get("repaymentAmount"));
        status = map.get("status");
        type = map.get("type");
    }

    public Date getDue() {
        return due;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public double getRepaymentAmount() {
        return repaymentAmount;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Type: ").append(type).append("\n");
        str.append("Status: ").append(status).append("\n");
        str.append("ID: ").append(id).append("\n");
        str.append("Repayment Amount: ").append(repaymentAmount).append("\n");
        Calendar c = Calendar.getInstance();
        c.setTime(due);
        str.append("Due: ").append(due);
        return str.toString();
    }
}
