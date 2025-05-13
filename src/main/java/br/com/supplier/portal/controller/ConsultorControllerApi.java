package br.com.supplier.portal.controller;

import br.com.supplier.portal.model.entity.ClienteEntity;
import br.com.supplier.portal.model.entity.ConsultorEntity;
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

@Tag(name = "Consultor", description = "Responsável pelo gerenciamento dos consultores.")
public interface ConsultorControllerApi {

    @Operation(summary = "Criar novo consultor.", tags = {"consultor", "salvar"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/consultores")
    public ResponseEntity<ConsultorEntity> salvar(@RequestBody ConsultorEntity consultor);

    @Operation(summary = "Endpoint responsável por atualizar os consultores.", tags = {"consultor", "atualizar"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/consultores/{id}")
    public ResponseEntity<ConsultorEntity> atualizar(@PathVariable("id") Integer id, @RequestBody ConsultorEntity consultor);

    @Operation(summary = "Endpoint responsável por excluir os consultores.", tags = {"consultor", "excluir"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/consultores/{id}")
    public ResponseEntity<ConsultorEntity> excluir(@PathVariable("id") Integer id, @RequestBody ConsultorEntity consultor);

    @Operation(summary = "Endpoint responsável por consultar o consultor por id.", tags = {"consultor", "consultar"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @GetMapping("/consultores/{id}")
    public ResponseEntity<ConsultorEntity> listarPorId(@PathVariable("id") Integer id);

    @Operation(description = "Endpoint responsável por listar os consultores sem paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/consultores", produces = "application/json")
    public ResponseEntity<List<ConsultorEntity>> listar();

    @Operation(description = "Endpoint responsável por listar os consultores com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/consultores/page", produces = "application/json")
    public ResponseEntity<Page<ConsultorEntity>> listar(Pageable consultor);

    @Operation(description = "Endpoint responsável por listar os consultores com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ConsultorEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/consultores/pages", produces = "application/json")
    public ResponseEntity<Map<String, Object>> listarConsultoresPage();

}
