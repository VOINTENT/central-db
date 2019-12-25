package ru.kpfu.icmit.client4.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceGenerator(name = "idGenerator", sequenceName = "offer_seq", allocationSize=1)
public class Offer extends IdEntity {
    @ManyToOne
    @JoinColumns(value = {@JoinColumn(referencedColumnName = "id")},
            foreignKey = @ForeignKey(name = "fk_offer_nomenclature"))
    private Nomenclature nomenclature;
    private Double price;
    private Integer count;

    @Override
    public String toString(){
        return nomenclature.toString() +" - "
                + "Цена :" + price.toString() + " - "
                + "Количество: " + count.toString();
    }

    public String toString2(Organization organization){
        return "Предложение организацией \"" + organization.toString()
                + "\" в количестве " + count.toString();
    }
}
