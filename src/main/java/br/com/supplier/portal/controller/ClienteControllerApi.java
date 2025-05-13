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

@Tag(name = "Cliente", description = "Responsável pelo gerenciamento dos clientes.")
public interface ClienteControllerApi {

    @Operation(summary = "Criar novo cliente.", tags = {"cliente", "salvar"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/clientes")
    public ResponseEntity<ClienteEntity> salvar(@RequestBody ClienteEntity cliente);

    @Operation(summary = "Endpoint responsável por atualizar os clientes.", tags = {"cliente", "atualizar"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteEntity> atualizar(@PathVariable("id") Integer id, @RequestBody ClienteEntity cliente);

    @Operation(summary = "Endpoint responsável por excluir os clientes.", tags = {"cliente", "excluir"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<ClienteEntity> excluir(@PathVariable("id") Integer id, @RequestBody ClienteEntity cliente);

    @Operation(summary = "Endpoint responsável por consultar o cliente por id.", tags = {"cliente", "consultar"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())})})
    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteEntity> listarPorId(@PathVariable("id") Integer id);

    @Operation(description = "Endpoint responsável por listar os clientes sem paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/clientes", produces = "application/json")
    public ResponseEntity<List<ClienteEntity>> listar();

    @Operation(description = "Endpoint responsável por listar os clientes com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/clientes/page", produces = "application/json")
    public ResponseEntity<Page<ClienteEntity>> listar(Pageable consultor);

    @Operation(description = "Endpoint responsável por listar os clientes com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content =
                    {@Content(schema = @Schema(implementation = ClienteEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(value = "/clientes/pages", produces = "application/json")
    public ResponseEntity<Map<String, Object>> listarClientesPage();

}
