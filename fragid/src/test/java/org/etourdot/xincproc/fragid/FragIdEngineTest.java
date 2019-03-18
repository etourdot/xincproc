package org.etourdot.xincproc.fragid;

import org.etourdot.xincproc.fragid.model.FRAG_TYPE;
import org.etourdot.xincproc.fragid.model.FragId;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class FragIdEngineTest
{

    @Test
    public void execute()
    {
        FragIdEngine engine = new FragIdEngine();
        FragId fragId = engine.getFragId("char=,200;length=758,UTF-8;md5=ABCF,UTF-16");
        assertThat(fragId.getType(), equalTo(FRAG_TYPE.CHAR));
        assertThat(fragId.getPositionEnd(), equalTo(200));
        assertThat(fragId.getPositionStart(), equalTo(-1));
    }
}