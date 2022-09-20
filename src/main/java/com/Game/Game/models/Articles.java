package com.Game.Game.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "home_3")
public class Articles {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, columnDefinition = "int(10) unsigned NOT NULL")
    private Long id;

    @Column(name = "taracashrjan", columnDefinition = "varchar(254) NOT NULL")
    private String taracashrjan;

    @Column(name = "bnakavayr", columnDefinition = "varchar(254) NOT NULL")
    private String bnakavayr;

    @Column(name = "hamaynq", columnDefinition = "varchar(254) NOT NULL")
    private String hamaynq;

    @Column(name = "taxamas", columnDefinition = "varchar(254) NOT NULL")
    private String taxamas;

    @Column(name = "poxoc", columnDefinition = "varchar(254) NOT NULL")
    private String poxoc;

    @Column(name = "shenqTun", columnDefinition = "varchar(254) NOT NULL")
    private String shenqTun;

    @Column(name = "hark", columnDefinition = "varchar(254) NOT NULL")
    private String hark;

    @Column(name = "kod", columnDefinition = "varchar(254) NOT NULL")
    private String kod;

    @Column(name = "taradram", columnDefinition = "varchar(254) NOT NULL")
    private String taradram;

    @Column(name = "shenqiTesak", columnDefinition = "varchar(254) NOT NULL")
    private String shenqiTesak;

    @Column(name = "vnasvacutyun", columnDefinition = "varchar(254) NOT NULL")
    private String vnasvacutyun;

    @Column(name = "nshanakutyun", columnDefinition = "varchar(254) NOT NULL")
    private String nshanakutyun;

    @Column(name = "gorcakaliKod", columnDefinition = "varchar(254) NOT NULL")
    private String gorcakaliKod;





    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Image image;

}
