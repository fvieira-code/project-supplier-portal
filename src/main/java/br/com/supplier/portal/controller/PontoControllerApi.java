package br.com.supplier.portal.controller;

import br.com.supplier.portal.model.entity.PontoEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Ponto", description = "Responsável pelo gerenciamento dos pontos.")
public interface PontoControllerApi {

    @Operation(summary = "Criar novo ponto.", tags = {"ponto", "salvar"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PontoEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/pontos")
    public ResponseEntity<PontoEntity> salvar(@RequestBody PontoEntity ponto);

    @Operation(summary = "Endpoint responsável por atualizar os pontos.", tags = {"ponto", "atualizar"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PontoEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/pontos/{id}")
    public ResponseEntity<PontoEntity> atualizar(@PathVariable("id") Integer id, @RequestBody PontoEntity ponto);

    @Operation(summary = "Endpoint responsável por excluir os pontos.", tags = {"ponto", "excluir"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PontoEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/pontos/{id}")
    public ResponseEntity<PontoEntity> excluir(@PathVariable("id") Integer id, @RequestBody PontoEntity ponto);

    @Operation(description = "Endpoint responsável por listar os pontos sem paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = PontoEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/pontos", produces = "application/json")
    public ResponseEntity<List<PontoEntity>> listar();

    @Operation(description = "Endpoint responsável por listar os pontos com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = PontoEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/pontos/page", produces = "application/json")
    public ResponseEntity<Page<PontoEntity>> listar(Pageable ponto);

    @Operation(description = "Endpoint responsável por listar os pontos com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = PontoEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/pontos/pages", produces = "application/json")
    public ResponseEntity<Map<String, Object>> listarConsultoresPage();

}
