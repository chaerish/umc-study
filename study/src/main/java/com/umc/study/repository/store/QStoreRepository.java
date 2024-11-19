package com.umc.study.repository.store;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.study.domain.QStore;
import com.umc.study.domain.Store;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QStoreRepository implements StoreRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QStore store;
    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        /*
        //BooleanBuilder은 QueryDSL에서 여러 개의 조건을 조합하기 위해 제공되는 빌더 클래스이다.
         */
        BooleanBuilder predicate = new BooleanBuilder();
        if(name!=null){
             predicate.and(store.storeName.eq(name));
        }
        if(score!=null){
            predicate.and(store.score.goe(4.0f));
        }
        return queryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }
}
