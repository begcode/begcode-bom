package tech.jhipster.service.aggregate;

import java.io.Serializable;

public class  StatField<A,G> implements Serializable {
    private A aggregate;
    private G groupBy;

    public StatField() {
    }

    public StatField(A aggregate, G groupBy) {
        this.aggregate = aggregate;
        this.groupBy = groupBy;
    }

    public StatField(StatField<A,G> statField) {
        this.aggregate = statField.aggregate;
        this.groupBy = statField.groupBy;
    }

    public A getAggregate() {
        return aggregate;
    }

    public void setAggregate(A aggregate) {
        this.aggregate = aggregate;
    }

    public G getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(G groupBy) {
        this.groupBy = groupBy;
    }
}
