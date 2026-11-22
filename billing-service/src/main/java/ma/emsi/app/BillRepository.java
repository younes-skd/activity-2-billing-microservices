package ma.emsi.app;

import org.springframework.data.jpa.repository.JpaRepository;
public interface BillRepository extends JpaRepository<Bill,Long> {}
