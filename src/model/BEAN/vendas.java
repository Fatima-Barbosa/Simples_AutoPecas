package model.BEAN;

import java.util.Date;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Fátima
 */
public class vendas {
    private SimpleStringProperty dataV;
    private SimpleDoubleProperty valorTotal;
    private SimpleLongProperty id;

    public vendas() {
    }

    public vendas(Double valorTotal) {
        this.valorTotal = new SimpleDoubleProperty(valorTotal);
    }

    public vendas(String dataV, Double valorTotal, Long id) {
        this.dataV = new SimpleStringProperty(dataV);
        this.valorTotal = new SimpleDoubleProperty(valorTotal);
        this.id = new SimpleLongProperty(id);
    }
    
    public SimpleStringProperty getDataV() {
        return dataV;
    }

    public void setDataV(SimpleStringProperty dataV) {
        this.dataV = dataV;
    }

    public SimpleDoubleProperty getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(SimpleDoubleProperty valorTotal) {
        this.valorTotal = valorTotal;
    }

    public SimpleLongProperty getId() {
        return id;
    }

    public void setId(SimpleLongProperty id) {
        this.id = id;
    }

    public String inverteData(String dataInicio) {
        String data1 = "";
        String dia = dataInicio.substring(8, 10);
        String mes = dataInicio.substring(5, 7);
        String ano = dataInicio.substring(0, 4);
        data1 += dia + "/" + mes + "/" + ano;
//        System.out.println("data1:"+data1);
        return data1;
    }
}
