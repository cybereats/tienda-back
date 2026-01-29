package cybereats.fpmislata.com.tiendaback.scheduler;

import cybereats.fpmislata.com.tiendaback.persistence.dao.jpa.entity.PCJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingExpirationScheduler {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Ejecuta cada minuto para verificar reservas expiradas y liberar PCs
   */
  @Scheduled(fixedRate = 60000) // cada 60 segundos
  @Transactional
  public void releaseExpiredBookings() {
    // Buscar PCs ocupados cuyas reservas han expirado
    String sql = """
        SELECT DISTINCT p.* FROM pc p
        INNER JOIN booking b ON b.pc_id = p.id
        WHERE p.status = 'OCCUPIED'
        AND DATE_ADD(b.created_at, INTERVAL b.hours HOUR) < NOW()
        """;

    @SuppressWarnings("unchecked")
    List<PCJpaEntity> expiredPCs = entityManager.createNativeQuery(sql, PCJpaEntity.class)
        .getResultList();

    for (PCJpaEntity pc : expiredPCs) {
      pc.setStatus("AVAILABLE");
      entityManager.merge(pc);
      System.out.println("PC liberado automáticamente: " + pc.getLabel() + " (ID: " + pc.getId() + ")");
    }

    if (!expiredPCs.isEmpty()) {
      System.out.println("Total de PCs liberados: " + expiredPCs.size());
    }
  }
}
