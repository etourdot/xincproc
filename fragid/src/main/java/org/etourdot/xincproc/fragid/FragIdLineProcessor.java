package org.etourdot.xincproc.fragid;

import com.google.common.io.LineProcessor;

import java.io.IOException;

public class FragIdLineProcessor implements LineProcessor<String>
{
    private final FragId fragId;
    private final StringBuffer data;
    private long linePos;
    private long curPos;

    public FragIdLineProcessor(final FragId fragId)
    {
        this.fragId = fragId;
        this.data = new StringBuffer();
        this.linePos = 0;
        this.curPos = 0;
    }

    @Override
    public boolean processLine(final String line)
    {
        if (FRAG_TYPE.CHAR == fragId.getType())
        {
            data.append(selectChars(line));
        }
        else
        {
            data.append(selectLines(line));
        }
        linePos++;
        return fragId.getType() != FRAG_TYPE.LINE ||
                fragId.getPositionEnd() <= 0 ||
                linePos <= fragId.getPositionEnd();
    }

    @Override
    public String getResult()
    {
        return data.toString();
    }

    private String selectChars(final String line)
    {
        final StringBuffer buffer = new StringBuffer();
        final long endcp = curPos + line.length() + 1;

        final String newLine;
        if (curPos < fragId.getPositionStart() && endcp > fragId.getPositionStart())
        {
            newLine = line.substring((int) (fragId.getPositionStart() - curPos));
            curPos = fragId.getPositionStart();
        }
        else
        {
            newLine = line;
        }

        if (curPos >= fragId.getPositionStart() && (curPos < fragId.getPositionEnd() || fragId.getPositionEnd() < 0))
        {
            final long rest = fragId.getPositionEnd() - curPos;
            if (rest < 0 || rest > newLine.length())
            {
                buffer.append(newLine).append("\n");
                curPos = endcp;
            } else
            {
                buffer.append(newLine.substring(0, (int) rest));
                curPos += rest;
            }
        }

        curPos = endcp;

        return buffer.toString();
    }

    private String selectLines(final String line)
    {
        final StringBuffer data = new StringBuffer();
        if (linePos >= fragId.getPositionStart() && (linePos < fragId.getPositionEnd() || fragId.getPositionEnd() < 0))
        {
            data.append(line).append("\n");
        }
        return data.toString();
    }
}
