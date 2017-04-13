package com.alirabiee.miniurl.data.miniurl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ali.
 */
@Data
@Entity
@Table( indexes = @Index( name = "originalUrl", columnList = "originalUrl", unique = false ) )
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniUrl {
    @Id
    @GeneratedValue
    private Long id;
    private Integer hashCode;
    private String originalUrl;
    private Long hits = 0L;

    public Integer getHashCode() {
        if ( hashCode != null ) {
            return Math.max( 1, Math.abs( hashCode / 10111 ) ); // only to make it a small positive
        }

        return null;
    }
}
