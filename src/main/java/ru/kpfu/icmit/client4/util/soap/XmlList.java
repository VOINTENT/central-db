package ru.kpfu.icmit.client4.util.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.icmit.client4.model.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlList<T> extends Content {

    @XmlElements(value = {
            @XmlElement(name = "nomenclature", type = Nomenclature.class),
            @XmlElement(name = "contract", type = Contract.class),
            @XmlElement(name = "demand", type = Demand.class),
            @XmlElement(name = "metric", type = Metric.class),
            @XmlElement(name = "offer", type = Offer.class),
            @XmlElement(name = "organization", type = Organization.class)
    })
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
