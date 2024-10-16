package kr.co.kjc.java8_study.repository.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.kjc.java8_study.domain.jpa.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface MemberJpaRepository extends JpaRepository<Member, Long>,
    QueryByExampleExecutor<Member>, CustomMemberJpaRepository {

}

interface CustomMemberJpaRepository {

}

@Repository
@RequiredArgsConstructor
class CustomMemberJpaRepositoryImpl implements CustomMemberJpaRepository {

  private final JPAQueryFactory queryFactory;

}
