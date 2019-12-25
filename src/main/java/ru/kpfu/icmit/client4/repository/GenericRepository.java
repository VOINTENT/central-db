package ru.kpfu.icmit.client4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.kpfu.icmit.client4.model.BaseEntity;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends CrudRepository<T, Long> {

}
