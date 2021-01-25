package com.epam.preprod.Pavlov.entity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order<P> {
    LocalDateTime localDateTime;
    private List<P> list;

    public Order(LocalDateTime localDateTime, List<P> list) {
        this.localDateTime = localDateTime;
        this.list = list;
    }

    public Order(Order<P> order) {
        this.localDateTime = order.getLocalDateTime();
        this.list = order.list;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<P> getList() {
        return list;
    }

    public void setList(List<P> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order<?> order = (Order<?>) o;
        return Objects.equals(localDateTime, order.localDateTime) &&
                Objects.equals(list, order.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDateTime, list);
    }

    @Override
    public String toString() {
        return "Order{" +
                "localDateTime=" + localDateTime +
                ", list=" + list +
                '}';
    }
}
