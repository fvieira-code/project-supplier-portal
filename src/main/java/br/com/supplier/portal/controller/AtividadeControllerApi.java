package br.com.supplier.portal.controller;

import br.com.supplier.portal.model.entity.AtividadeEntity;
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

@Tag(name = "Atividade", description = "Responsável pelo gerenciamento dos atividades.")
public interface AtividadeControllerApi {

    @Operation(summary = "Criar nova atividade.", tags = {"atividade", "salvar"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = AtividadeEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/atividades")
    public ResponseEntity<AtividadeEntity> salvar(@RequestBody AtividadeEntity atividade);

    @Operation(summary = "Endpoint responsável por atualizar os consultores.", tags = {"atividade", "atualizar"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AtividadeEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/atividades/{id}")
    public ResponseEntity<AtividadeEntity> atualizar(@PathVariable("id") Integer id, @RequestBody AtividadeEntity atividade);

    @Operation(summary = "Endpoint responsável por excluir os atividades.", tags = {"atividade", "excluir"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AtividadeEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/atividades/{id}")
    public ResponseEntity<AtividadeEntity> excluir(@PathVariable("id") Integer id, @RequestBody AtividadeEntity atividade);

    @Operation(description = "Endpoint responsável por listar os consultores sem paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = AtividadeEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/atividades", produces = "application/json")
    public ResponseEntity<List<AtividadeEntity>> listar();

    @Operation(description = "Endpoint responsável por listar os atividades com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = AtividadeEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/atividades/page", produces = "application/json")
    public ResponseEntity<Page<AtividadeEntity>> listar(Pageable atividade);

    @Operation(description = "Endpoint responsável por listar os atividades com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = AtividadeEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/atividades/pages", produces = "application/json")
    public ResponseEntity<Map<String, Object>> listarAtividadesPage();

}
