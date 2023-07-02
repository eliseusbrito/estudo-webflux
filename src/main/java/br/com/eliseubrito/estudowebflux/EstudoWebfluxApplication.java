package br.com.eliseubrito.estudowebflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;


@SpringBootApplication
//@Slf4j
public class EstudoWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstudoWebfluxApplication.class, args);
        System.out.println("Rodou o main");
//        mergeOperator();
//        mapOperator();
//        logOperator();
//        justMono();
//        justOrEmptyMono();
//        justOrEmptyMonoNull();
//        justOrEmptyMonoNullDefaltifEmpty();
//        justOrEmptyMonoIntDefaltifEmpty();
//        justOrEmptyWithSwitchEmpty();
//        mapMono();
//        mapMonoAlteraClass();
//        filterEx1();
//        filterEx2();
//        filterAndDefaultIfEmpty();
//        justFluxString();
//        justFluxIntegers();
//        justFluxFromIterable();
//        mapFluxEx1();
        mapFluxEx2();
//        collectList();
//        filterFlux();
    }


    public static void mergeOperator() {
        System.out.println("iniciou");

        Flux<String> flux1 = Flux.just("a", "b").log(); //gera um flux array e quando coloca o .log se transforma em FluxLogFuseable
//        flux1.subscribe();
        Flux<String> flux2 = Flux.just("c", "d").log(); //gera um flux array e quando coloca o .log se transforma em FluxLogFuseable
        flux2.subscribe();

        Flux<String> mergeFlux = Flux.merge(flux1, flux2).log(); //gera um FluxMerge mas não se consegue ver o conteudo diretamente dentro dele e quando coloca o .log se transforma em FluxLogFuseable
        mergeFlux.subscribe();
        System.out.println(mergeFlux.toString());
        System.out.println("terminou");

//        mergeFlux.subscribe(log::info);

    }

    public static void mapOperator() {
        System.out.println("iniciou map Operator");
//        log.info("Rodou log.info mapOperator");
        Flux<String> flux1 = Flux.just("a", "b")
                .map(String::toUpperCase).log(); // gera um FluxMapFuseable e quando coloca o .log se transforma em FluxLogFuseable
        flux1.subscribe();
//        log.info("flux1 mapOperator", flux1);


    }

    public static void logOperator() {
        System.out.println("iniciou map Operator");
//        log.info("Rodou log.info logOperator");
        Mono<String> mono = Mono.just("name").log(); // gera um MonoJust e quando coloca o .log se transforma em MonoLogFuseable
        mono.subscribe();
//        log.info("mono logOperator", mono);


    }

    public static void justMono() {
        System.out.println("iniciou justMono");
        Mono.just(1).doOnSuccess(System.out::println).log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void justOrEmptyMono() {
        System.out.println("iniciou justOrEmptyMono");
        Mono.justOrEmpty(1).doOnSuccess(System.out::println).log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void justOrEmptyMonoNull() {
        System.out.println("iniciou justOrEmptyMonoNull");
        Mono.justOrEmpty(null).doOnSuccess(System.out::println).log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void justOrEmptyMonoNullDefaltifEmpty() {
        System.out.println("iniciou justOrEmptyMonoNullDefaltifEmpty");
        Mono.justOrEmpty(Optional.empty())
                .defaultIfEmpty(6)
                .doOnSuccess(System.out::println)
                .log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void justOrEmptyMonoIntDefaltifEmpty() {
        System.out.println("iniciou justOrEmptyMonoNullDefaltifEmpty");
        Mono.justOrEmpty(2)
                .defaultIfEmpty(6)
                .doOnSuccess(System.out::println)
                .log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void justOrEmptyWithSwitchEmpty() {
        System.out.println("iniciou justOrEmptyWithSwitchEmpty");
        Mono.justOrEmpty(Optional.empty())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new Exception())))
                .doOnSuccess(System.out::println)
                .log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void mapMono() {
        System.out.println("iniciou mapMono");
        Mono.just(2)
                .map(n -> n * 2)
                .doOnSuccess(System.out::println)
                .log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void mapMonoAlteraClass() {
        System.out.println("mapMonoAlteraClass");
        Mono.just(2)
                .map(n -> String.valueOf(n))
                .doOnSuccess(System.out::println)
                .doOnNext(n -> System.out.println(n.getClass()))
                .log().subscribe(); // se não colocar o subscribe não imprime
    }

    public static void filterEx1() {
        System.out.println("filterEx1");
        Mono.just(2)
                .filter(a -> a % 2 == 0)
                .doOnSuccess(System.out::println)
                .log().subscribe();
    }

    public static void filterEx2() {
        System.out.println("filterEx2");
        Mono.just(3)
                .filter(a -> a % 2 == 0)
                .doOnSuccess(System.out::println)
                .log().subscribe();
    }

    public static void filterAndDefaultIfEmpty() {
        System.out.println("filterAndSwitchIfEmpty");
        Mono.just(4)
                .filter(a -> a % 2 == 0)
                .defaultIfEmpty(6)
                .doOnSuccess(System.out::println)
                .log().subscribe();
    }

    public static void justFluxString() {
        System.out.println("justFluxString");
        Flux.just("Willian", "Suane", "DevDojo", "Academy")
                .doOnNext(System.out::println)
                .log().subscribe();
    }

    public static void justFluxIntegers() {
        System.out.println("justFluxIntegers");
        Flux.just(1, 2, 3, 4, 5)
                .doOnNext(System.out::println)
                .log().subscribe();
    }

    public static void justFluxFromIterable() {
        System.out.println("justFluxFromIterable");
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .doOnNext(System.out::println)
                .log().subscribe();
    }

    public static void mapFluxEx1() {
        System.out.println("mapEx1");
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .map(n -> n * 2)
                .doOnNext(System.out::println)
                .log().subscribe();
    }

    public static void mapFluxEx2() {
        System.out.println("mapEx2");
        Flux.just("Willian", "Suane", "Davi", "Oi")
                .map(n -> n.length())
                .doOnNext(System.out::println)
                .log().subscribe();
    }

    public static void collectList() {
        System.out.println("mapEx1");
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .map(n -> n * 2)
                .collectList()
                .doOnNext(System.out::println)
                .log().subscribe();
    }

    public static void filterFlux() {
        System.out.println("filterFlux");
        Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6))
                .map(n -> n * 15)
                .filter(n -> n %2 == 0)
                .doOnNext(System.out::println)
                .log().subscribe();
    }

}
