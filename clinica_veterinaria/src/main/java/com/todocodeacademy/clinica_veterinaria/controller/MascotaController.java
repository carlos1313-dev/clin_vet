package com.todocodeacademy.clinica_veterinaria.controller;
import com.todocodeacademy.clinica_veterinaria.dto.MascoDuenioDTO;
import com.todocodeacademy.clinica_veterinaria.model.Mascota;
import com.todocodeacademy.clinica_veterinaria.service.IMascotaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MascotaController {

    @Autowired
    private IMascotaService mascoServ;

    @GetMapping(value = "/mascotas/traer", produces = "application/json")
public ResponseEntity<List<Mascota>> traerMascotas() {
    try {
        List<Mascota> mascotas = mascoServ.getMascotas();
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(mascotas);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

    @PostMapping("/mascotas/crear")
    public String savePersona(@RequestBody Mascota masco) {
        mascoServ.saveMascota(masco);

        return "La mascota fue creada correctamente";
    }

    @DeleteMapping("/mascotas/borrar/{id_mascota}")
    public String deleteMascota(@PathVariable Long id_mascota) {
        mascoServ.deleteMascota(id_mascota);
        return "La mascota fue borrada correctamente";
    }
    
     @PutMapping("/mascotas/editar")
    public String editMascota(@RequestBody Mascota masco) {
        
        mascoServ.editMascota(masco);
        return "La mascota fue borrada correctamente";
    }
    
      @GetMapping("/mascotas/traer-caniches")
    public List<Mascota> traerCaniches() {
        return mascoServ.getCaniches();
    }
    
    @GetMapping ("mascotas/traer-duenios")
    public List<MascoDuenioDTO> traerMascoDuenios () {
        
        return mascoServ.getMascoDuenios();
    
    }

}
