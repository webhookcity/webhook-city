package de.bayerl.city.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "REQUESTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO return the proper error message if a violation occurs
    @Size(max=1000)
    private String url;
    @Size(max=3000)
    private String headers;
    @Size(max=10)
    private String method;
    @Size(max=5000)
    private String body;

    @Column(name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;

    
}
