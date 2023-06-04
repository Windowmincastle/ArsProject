package castle.ArsProject.repository;

import castle.ArsProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<UserEntity, Long> {
    // 추가적인 쿼리 메서드 정의 (필요한 경우)
    // ...
}

