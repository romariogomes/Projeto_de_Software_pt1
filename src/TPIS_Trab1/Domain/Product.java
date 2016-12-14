package TPIS_Trab1.Domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product implements Serializable {

    private Integer productId;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    Product() {}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(productId).append(" - ").append(name).append("\n");
        sb.append(description).append("\n");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        sb.append("Início do Projeto: ").append(dateFormat.format(startDate)).append("\n");
        sb.append("Finalização do Projeto: ").append(dateFormat.format(endDate)).append("\n");

        return sb.toString();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
