package pl.lodz.p.it.ssbd2021.ssbd01.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Typ Medical documentation.
 */
@Entity
@Table(name = "medical_documentations")
@NamedQueries({
        @NamedQuery(name = "MedicalDocumentation.findAll", query = "SELECT m FROM MedicalDocumentation m"),
        @NamedQuery(name = "MedicalDocumentation.findById", query = "SELECT m FROM MedicalDocumentation m WHERE m.id = :id"),
        @NamedQuery(name = "MedicalDocumentation.findByAllergies", query = "SELECT m FROM MedicalDocumentation m WHERE m.allergies = :allergies"),
        @NamedQuery(name = "MedicalDocumentation.findByMedicationsTaken", query = "SELECT m FROM MedicalDocumentation m WHERE m.medicationsTaken = :medicationsTaken"),
        @NamedQuery(name = "MedicalDocumentation.findByVersion", query = "SELECT m FROM MedicalDocumentation m WHERE m.version = :version"),
        @NamedQuery(name = "MedicalDocumentation.findByCreationDateTime", query = "SELECT m FROM MedicalDocumentation m WHERE m.creationDateTime = :creationDateTime"),
        @NamedQuery(name = "MedicalDocumentation.findByModificationDateTime", query = "SELECT m FROM MedicalDocumentation m WHERE m.modificationDateTime = :modificationDateTime")})
public class MedicalDocumentation extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_documentations_generator")
    @SequenceGenerator(name = "medical_documentations_generator", sequenceName = "medical_documentations_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "allergies")
    private String allergies;

    @Column(name = "medications_taken")
    private String medicationsTaken;

    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, updatable = false)
    @ManyToOne(optional = false)
    private Account patient;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "documentation_id", nullable = false)
    private Collection<DocumentationEntry> documentationEntryCollection = new ArrayList<DocumentationEntry>();

    /**
     * Tworzy nową instancję klasy MedicalDocumentation.
     */
    public MedicalDocumentation() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getMedicationsTaken() {
        return medicationsTaken;
    }

    public void setMedicationsTaken(String medicationsTaken) {
        this.medicationsTaken = medicationsTaken;
    }

    public Account getPatient() {
        return patient;
    }

    public Collection<DocumentationEntry> getDocumentationEntryCollection() {
        return documentationEntryCollection;
    }

    @Override
    public String toString() {
        return "pl.lodz.p.it.ssbd2021.ssbd01.entities.MedicalDocumentation[ id=" + id + " ]";
    }

}
