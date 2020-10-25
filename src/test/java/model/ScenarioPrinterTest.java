package model;


import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ScenarioPrinterTest {

    @Test
    void print() {
        // given
        Scenario scenarioMock = mock(Scenario.class);
        PrintStream streamMock = mock(PrintStream.class);
        BiMap<Position, Integer> expectedPositions = ImmutableBiMap.of(
                new Position(1,1), 0,
                new Position(2,2), 1);
        given(scenarioMock.getMowersPositions()).willReturn(expectedPositions);
        Map<Integer, Orientation> expectedOrientations = ImmutableMap.of(
                0 , new Orientation('N'),
                1 , new Orientation('S'));
        given(scenarioMock.getMowersOrientations()).willReturn(expectedOrientations);

        // when
        ScenarioPrinter.print(scenarioMock, streamMock);

        // then
        verify(streamMock, times(1)).println("1 1 N");
        verify(streamMock, times(1)).println("2 2 S");
    }
}