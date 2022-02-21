package com.yaojinwei.study.es.test;

import org.elasticsearch.search.DocValueFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */

@Table(name="open_parent_child_test_agg_2019_v3")
public class TestEntity {
    @Column(name="or_name")
    private String name;
    @Column(name="or_age")
    private Integer age;
    @Id
    @Column(name="or_patient_id")
    private String patientId;
    @Column(name="or_race")
    private String race;

    private String dischargeDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }
}
