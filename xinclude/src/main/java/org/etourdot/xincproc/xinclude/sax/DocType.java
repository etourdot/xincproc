package org.etourdot.xincproc.xinclude.sax;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: etourdot
 * Date: 06/03/13
 * Time: 22:42
 */
class DocType {
    private String doctype;
    private String publicId;
    private String systemId;
    private ImmutableList<Element> elements = ImmutableList.of();
    private ImmutableMap<String, ImmutableList<Attribute>> attributes = ImmutableMap.of();
    private ImmutableList<ExternalEntity> externalEntities = ImmutableList.of();
    private ImmutableList<InternalEntity> internalEntities = ImmutableList.of();
    private ImmutableMap<String, UnparsedEntity> unparsedEntities = ImmutableMap.of();

    public static DocType copy(final DocType docTypeToCopy)
    {
        final DocType newDocType = new DocType();
        newDocType.doctype = docTypeToCopy.doctype;
        newDocType.publicId = docTypeToCopy.publicId;
        newDocType.systemId = docTypeToCopy.systemId;
        newDocType.elements = ImmutableList.copyOf(docTypeToCopy.elements);
        newDocType.attributes = ImmutableMap.copyOf(docTypeToCopy.attributes);
        newDocType.externalEntities = ImmutableList.copyOf(docTypeToCopy.externalEntities);
        newDocType.internalEntities = ImmutableList.copyOf(docTypeToCopy.internalEntities);
        newDocType.unparsedEntities = ImmutableMap.copyOf(docTypeToCopy.unparsedEntities);
        return newDocType;
    }

    public DocType setDoctype(final String doctype)
    {
        this.doctype = doctype;
        return this;
    }

    public DocType setPublicId(final String publicId)
    {
        this.publicId = publicId;
        return this;
    }

    public DocType setSystemId(final String systemId)
    {
        this.systemId = systemId;
        return this;
    }

    public DocType addElement(final String name, final String model)
    {
        this.elements = new ImmutableList.Builder<Element>().addAll(elements).add(new Element(name, model)).build();
        return this;
    }

    public DocType addAttribute(final String eName, final String aName, final String type, final String mode,
                                final String value)
    {
        final Attribute newAttribute = new Attribute(aName, type, mode, value);
        final ImmutableList.Builder<Attribute> listAttributeBuilder = new ImmutableList.Builder<Attribute>();
        if (attributes.get(eName) != null)
        {
            listAttributeBuilder.addAll(attributes.get(eName));
        }
        listAttributeBuilder.add(newAttribute);
        this.attributes = new ImmutableMap.Builder<String, ImmutableList<Attribute>>().put(eName,
                listAttributeBuilder.build()).build();
        return this;
    }

    public DocType addExternalEntity(final String name, final String publicId, final String systemId)
    {
        this.externalEntities = new ImmutableList.Builder<ExternalEntity>().addAll(externalEntities)
                .add(new ExternalEntity(name, publicId, systemId)).build();
        return this;
    }

    public DocType addInternalEntity(final String name, final String value)
    {
        this.internalEntities = new ImmutableList.Builder<InternalEntity>().addAll(internalEntities)
                .add(new InternalEntity(name, value)).build();
        return this;
    }

    public DocType addUnparsedEntity(final String name, final String publicId, final String systemId,
                                     final String notationName)
            throws XIncludeFatalException
    {
        final UnparsedEntity unparsedEntity = unparsedEntities.get(name);
        if (unparsedEntity != null && systemId != null) {
            if (!systemId.equals(unparsedEntity.getSystemId()))
            {
                throw new XIncludeFatalException("duplicate unparsed entity");
            }
        }
        this.unparsedEntities = new ImmutableMap.Builder<String, UnparsedEntity>().put(name,
                new UnparsedEntity(notationName, publicId, systemId)).build();
        return this;
    }

    public String getDocTypeValue()
    {
        final StringBuffer docTypeBuffer = new StringBuffer();
        if (doctype != null)
        {
            docTypeBuffer.append("<!DOCTYPE ").append(doctype);
            if (systemId != null)
            {
                if (publicId != null)
                {
                    docTypeBuffer.append("PUBLIC \"").append(publicId).append("\" \"").append(systemId).append("\"");
                } else {
                    docTypeBuffer.append("SYSTEM \"").append(systemId).append("\"");
                }
            }
            if (!elements.isEmpty())
            {
                docTypeBuffer.append(" [");
                for (final Element element : elements)
                {
                    docTypeBuffer.append("\n<!ELEMENT ").append(element.getName()).append(" ")
                            .append(element.getValue()).append(">");
                    final ImmutableList<Attribute> attributesList = attributes.get(element.getName());
                    if (attributesList != null && !attributesList.isEmpty())
                    {
                        docTypeBuffer.append("\n<!ATTLIST ").append(element.getName()).append(" ");
                        for (final Attribute attribute : attributesList)
                        {
                            docTypeBuffer.append(attribute.getaName())
                                .append(" ").append(attribute.getType()).append(" ").append(attribute.getMode())
                                .append(" ").append(Strings.nullToEmpty(attribute.getValue()));
                        }
                        docTypeBuffer.append(">");
                    }
                }
                for (final InternalEntity internalEntity : internalEntities)
                {
                    docTypeBuffer.append("\n<!ENTITY ").append(internalEntity.getName()).append(" ")
                            .append(internalEntity.getValue()).append(">");
                }
                for (final ExternalEntity externalEntity : externalEntities)
                {
                    docTypeBuffer.append("\n<!NOTATION ").append(externalEntity.getName()).append(" ")
                            .append(externalEntity.getPublicId()).append(" ").append(externalEntity.getSystemId())
                            .append(">");
                }
                docTypeBuffer.append("\n]");
            }
            docTypeBuffer.append(">\n");
        }
        return docTypeBuffer.toString();
    }

    private class Element {
        private String name;
        private String value;

        private Element(final String name, final String value)
        {
            this.name = name;
            this.value = value;
        }

        public String getName()
        {
            return name;
        }

        public String getValue()
        {
            return value;
        }
    }

    private class ExternalEntity {
        private String name;
        private String publicId;
        private String systemId;

        private ExternalEntity(final String name, final String publicId, final String systemId)
        {
            this.name = name;
            this.publicId = publicId;
            this.systemId = systemId;
        }

        public String getName() {
            return name;
        }

        public String getPublicId()
        {
            return publicId;
        }

        public String getSystemId()
        {
            return systemId;
        }
    }

    private class InternalEntity {
        private String name;
        private String value;

        private InternalEntity(final String name, final String value)
        {
            this.name = name;
            this.value = value;
        }

        public String getName()
        {
            return name;
        }

        public String getValue()
        {
            return value;
        }
    }
    private class Attribute {
        private String aName;
        private String type;
        private String mode;
        private String value;

        private Attribute(final String aName, final String type, final String mode, final String value)
        {
            this.aName = aName;
            this.type = type;
            this.mode = mode;
            this.value = value;
        }

        public String getaName()
        {
            return aName;
        }

        public String getMode()
        {
            return mode;
        }

        public String getType()
        {
            return type;
        }

        public String getValue()
        {
            return value;
        }
    }

    private class UnparsedEntity {
        private String publicId;
        private String systemId;
        private String notationName;

        private UnparsedEntity(final String notationName, final String publicId, final String systemId)
        {
            this.notationName = notationName;
            this.publicId = publicId;
            this.systemId = systemId;
        }

        public String getNotationName()
        {
            return notationName;
        }

        public String getPublicId()
        {
            return publicId;
        }

        public String getSystemId()
        {
            return systemId;
        }
    }
}
