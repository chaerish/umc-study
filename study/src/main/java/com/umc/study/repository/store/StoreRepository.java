package com.umc.study.repository.store;

import com.umc.study.domain.Mission;
import com.umc.study.domain.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
