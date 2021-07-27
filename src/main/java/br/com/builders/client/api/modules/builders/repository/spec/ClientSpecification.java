package br.com.builders.client.api.modules.builders.repository.spec;

import br.com.builders.client.api.modules.builders.entity.Client;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
public class ClientSpecification implements Specification<Client> {

    @Builder.Default
    private final transient Optional<String> name = Optional.empty();
    @Builder.Default
    private final transient Optional<LocalDate> birthDate = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        name.ifPresent(s -> predicates.add(builder.like(builder.lower(root.get("name")), "%" + s.toLowerCase() + "%")));
        birthDate.ifPresent(s -> predicates.add(builder.equal(root.get("birthDate"), s)));
        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
