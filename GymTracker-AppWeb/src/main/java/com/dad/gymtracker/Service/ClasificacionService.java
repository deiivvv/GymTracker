package com.dad.gymtracker.Service;

import com.dad.gymtracker.Dto.EstadisticasUsuarioDTO;
import com.dad.gymtracker.Dto.PerfilDTO;
import com.dad.gymtracker.Dto.UsuarioDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
@AllArgsConstructor
public class ClasificacionService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EstadisticasUsuarioDTO> buscarAllEstadisticasUsuariosNoBloqueados() {
        String sqlBuscarEstadisticas = "SELECT u.id, u.nombre, " +
                "COUNT(DISTINCT r.id) AS entrenos, " +
                "COUNT(DISTINCT re.id_ejercicio) AS ejercicios, " +
                "SUM(s.peso * s.repes) AS volumen, " +
                "COUNT(DISTINCT s.id) AS series " +
                "FROM usuarios u " +
                "LEFT JOIN rutinas r ON u.id = r.id_usuario " +
                "LEFT JOIN rutinas_ejercicios_series re ON r.id = re.id_rutina " +
                "LEFT JOIN series s ON re.id_serie = s.id " +
                "WHERE u.rol != 'bloqueado' " +
                "GROUP BY u.id, u.nombre";
        try {
            List<EstadisticasUsuarioDTO> resultado = entityManager.createNativeQuery(sqlBuscarEstadisticas, EstadisticasUsuarioDTO.class)
                    .getResultList();

            return resultado.stream()
                    .map(t -> EstadisticasUsuarioDTO.builder()
                            .id(t.getId())
                            .nombre(t.getNombre())
                            .series(t.getSeries())
                            .entrenos(t.getEntrenos())
                            .ejercicios(t.getEjercicios())
                            .volumen((t.getVolumen() !=null) ? t.getVolumen() : 0.0)
                            .build())
                            .collect(Collectors.toList());
        } catch (NoResultException e) {
            return null; // Devuelve null si no se encuentra ningún usuario
        }
    }


    public void ordenarUsuariosByEstadistica(List<EstadisticasUsuarioDTO> lista, String estadistica) {
        lista.sort((o1, o2) -> {
            try {
                Field field = EstadisticasUsuarioDTO.class.getDeclaredField(estadistica);
                field.setAccessible(true);
                Comparable value1 = (Comparable) field.get(o1);
                Comparable value2 = (Comparable) field.get(o2);
                return value2.compareTo(value1);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int bucarPosicionById(List<EstadisticasUsuarioDTO> lista, int idUsuario) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == idUsuario) {
                return i+1;
            }
        }
        return -1;
    }
}
