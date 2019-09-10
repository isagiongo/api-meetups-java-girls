package com.isagiongo.meetupsjavagirls.unit.services;

import com.isagiongo.meetupsjavagirls.enums.TipoEventoEnum;
import com.isagiongo.meetupsjavagirls.exceptions.MeetupEditionAlreadyExists;
import com.isagiongo.meetupsjavagirls.exceptions.MeetupNotFoundException;
import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.Talk;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import com.isagiongo.meetupsjavagirls.repository.TalkRepository;
import com.isagiongo.meetupsjavagirls.services.MeetupService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MeetupServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private MeetupService meetupService;

    @Mock
    private MeetupRepository meetupRepository;

    @Mock
    private TalkRepository talkRepository;

    @Test
    public void deveSalvarMeetup () {
        Meetup meetupSalvo = new Meetup(criaMeetup());

        LocalDate data = LocalDate.of(2018, Month.JUNE, 18);

        Talk talk1 = new Talk();
        talk1.setId("762hgsdb2");
        talk1.setTipo(TipoEventoEnum.CODING_DOJO);
        talk1.setTitulo("Testes unitários");
        List<String> palestrantes = Arrays.asList("Marina Moreira", "Juliana Ferreira");
        talk1.setPalestrantes(palestrantes);

        Talk talk2 = new Talk();
        talk2.setId("uiuier2287");
        talk2.setTipo(TipoEventoEnum.WORKSHOP);
        talk2.setTitulo("API com Spring Boot");
        List<String> palestrantes2 = Arrays.asList("Isa Giongo", "Ana Carolina Ferreira");
        talk2.setPalestrantes(palestrantes2);

        List<Talk> talks = new ArrayList<>();
        talks.add(talk1);
        talks.add(talk2);

        MeetupRequestDTO meetupDTO = new MeetupRequestDTO();
        meetupDTO.setLocalRealizacao("Global Tecnopuc");
        meetupDTO.setDataRealizacao(data);
        meetupDTO.setEdicao(1);
        meetupDTO.setTalks(talks);
        meetupDTO.setQuantidadeParticipantes(22);

        Meetup meetupEsperado = new Meetup(meetupDTO);

        when(meetupRepository.findByEdicao(1)).thenReturn(Optional.empty());
        meetupEsperado.setId("w2378fhe");
        meetupService.create(criaMeetup());

        Assert.assertEquals(meetupEsperado.getEdicao(),meetupSalvo.getEdicao());
        Assert.assertEquals(meetupEsperado.getLocalRealizacao(),meetupSalvo.getLocalRealizacao());
        Assert.assertEquals(meetupEsperado.getQuantidadeParticipantes(),meetupSalvo.getQuantidadeParticipantes());
    }

    @Test
    public void deveRetornarExcecaoAoSalvarEdicaoDeMeetupJaExistitente() {
        exception.expect(MeetupEditionAlreadyExists.class);
        exception.expectMessage("Essa edição do meetup já está cadastrada.");

        Meetup meetup = new Meetup(criaMeetup());
        when(meetupRepository.findByEdicao(1)).thenReturn(Optional.of(meetup));

        meetupService.create(criaMeetup());
    }

    @Test
    public void deveRetornarExcecaoAoBuscarMeetupNaoExistentePorId() {
        exception.expect(MeetupNotFoundException.class);
        exception.expectMessage("Meetup não encontrado.");

        meetupService.findById("1223dsds23");
    }

    @Test
    public void deveRetornarMeetupExistenteAoBuscarPorId() {
        Meetup meetupSalvo = new Meetup(criaMeetup());
        meetupSalvo.setId("1223dsds23");

        when(meetupRepository.findById("1223dsds23")).thenReturn(Optional.of(meetupSalvo));

        Meetup meetupRetornado = meetupService.findById("1223dsds23");

        Assert.assertNotNull(meetupRetornado);
    }

    @Test
    public void deveRetornarExcecaoAoBuscarMeetupNaoExistentePorEdicao() {
        exception.expect(MeetupNotFoundException.class);
        exception.expectMessage("Meetup não encontrado.");

        meetupService.findByEdicao(8);
    }

    @Test
    public void deveRetornarMeetupExistenteAoBuscarPorEdicao() {
        Meetup meetupSalvo = new Meetup(criaMeetup());

        when(meetupRepository.findByEdicao(1)).thenReturn(Optional.of(meetupSalvo));

        Meetup meetupRetornado = meetupService.findByEdicao(1);

        Assert.assertNotNull(meetupRetornado);
    }

    private MeetupRequestDTO criaMeetup() {
        LocalDate data = LocalDate.of(2018, Month.JUNE, 18);

        Talk talk1 = new Talk();
        talk1.setId("762hgsdb2");
        talk1.setTipo(TipoEventoEnum.CODING_DOJO);
        talk1.setTitulo("Testes unitários");
        List<String> palestrantes = Arrays.asList("Marina Moreira", "Juliana Ferreira");
        talk1.setPalestrantes(palestrantes);

        Talk talk2 = new Talk();
        talk2.setId("uiuier2287");
        talk2.setTipo(TipoEventoEnum.WORKSHOP);
        talk2.setTitulo("API com Spring Boot");
        List<String> palestrantes2 = Arrays.asList("Isa Giongo", "Ana Carolina Ferreira");
        talk2.setPalestrantes(palestrantes2);

        List<Talk> talks = new ArrayList<>();
        talks.add(talk1);
        talks.add(talk2);

        MeetupRequestDTO meetup = new MeetupRequestDTO();
        meetup.setLocalRealizacao("Global Tecnopuc");
        meetup.setDataRealizacao(data);
        meetup.setEdicao(1);
        meetup.setTalks(talks);
        meetup.setQuantidadeParticipantes(22);

        return meetup;
    }
}
