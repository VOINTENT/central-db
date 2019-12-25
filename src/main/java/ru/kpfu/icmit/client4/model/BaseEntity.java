package ru.kpfu.icmit.client4.model;

import lombok.Data;
import ru.kpfu.icmit.client4.util.soap.Content;

import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@MappedSuperclass
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseEntity extends Content {

}
