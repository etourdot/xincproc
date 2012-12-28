package org.etourdot.xincproc.xpointer.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 21/09/12
 * Time: 23:11
 */
public class Pointer {
    private ShortHand shortHand;
    private ImmutableList<PointerPart> schemeBased;

    public Pointer()
    {
        this.shortHand = null;
        this.schemeBased = new ImmutableList.Builder<PointerPart>().build();
    }

    public Pointer(final ShortHand shortHand)
    {
        this.shortHand = shortHand;
        this.schemeBased = new ImmutableList.Builder<PointerPart>().build();
    }

    public Pointer(final List<PointerPart> schemeBased)
    {
        this.shortHand = null;
        final ImmutableList.Builder<PointerPart> builder = new ImmutableList.Builder<PointerPart>();
        if (schemeBased != null)
        {
            builder.addAll(schemeBased);
        }
        this.schemeBased = builder.build();
    }

    public void addPointerPart(final PointerPart pointerPart)
    {
        this.schemeBased = new ImmutableList.Builder<PointerPart>().addAll(schemeBased).add(pointerPart).build();
    }

    public boolean isShortHand()
    {
        return shortHand != null;
    }

    public ShortHand getShortHand()
    {
        return shortHand;
    }

    public void setShortHand(final ShortHand shortHand)
    {
        this.shortHand = shortHand;
    }

    public boolean isSchemeBased()
    {
        return !schemeBased.isEmpty();
    }

    public ImmutableList<PointerPart> getSchemeBased()
    {
        return schemeBased;
    }

    public void setSchemeBased(final ImmutableList<PointerPart> schemeBased)
    {
        this.schemeBased = schemeBased;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        if (isShortHand())
        {
            builder.append(getShortHand().toString());
        }
        else
        {
            for (final PointerPart part : getSchemeBased())
            {
                builder.append(part.toString());
            }
        }
        return builder.toString();
    }
}
