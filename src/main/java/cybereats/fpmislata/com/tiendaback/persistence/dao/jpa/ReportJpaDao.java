package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;

public interface ReportJpaDao {
    ReportJpaEntity insert(ReportJpaEntity reportJpaEntity);

    ReportJpaEntity update(ReportJpaEntity reportJpaEntity);

    List<ReportJpaEntity> getAll();

    List<ReportJpaEntity> getByUserId(Long userId);

    List<ReportJpaEntity> getByPCId(Long pcId);

    Optional<ReportJpaEntity> getById(Long id);

    void deleteById(Long id);
}
