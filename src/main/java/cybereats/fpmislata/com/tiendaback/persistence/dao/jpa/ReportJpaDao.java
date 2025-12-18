package cybereats.fpmislata.com.tiendaback.persistence.dao.jpa;

import java.util.List;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.ReportJpaEntity;

public interface ReportJpaDao extends GenericJpaDao<ReportJpaEntity> {

    List<ReportJpaEntity> findByUserId(Long userId);

    List<ReportJpaEntity> findByPCId(Long pcId);

    List<ReportJpaEntity> findByStatus(String status);

    List<ReportJpaEntity> findByCreatedAt(String createdAt);
}
