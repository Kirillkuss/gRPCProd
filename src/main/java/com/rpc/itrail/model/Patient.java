package com.rpc.itrail.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Patient implements Serializable{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idPatient;

    @Column( name = "surname" )
    private String surname;

    @Column( name = "name" )
    private String name;

    @Column( name = "full_name")
    private String fullname;

    @Column( name = "gender" )
    private com.rpc.itrail.Gender gender;

    @Column ( name = "phone")
    private String phone;

    @Column( name = "address" )
    private String address;

    @OneToOne
    @Hidden
    @JoinColumn( name = "document_id", referencedColumnName = "id_document")
    private Document document;

}
