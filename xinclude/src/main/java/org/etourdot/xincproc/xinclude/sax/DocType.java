/*
 * This file is part of the XIncProc framework.
 * Copyright (C) 2011 - 2013 Emmanuel Tourdot
 *
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this software.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package org.etourdot.xincproc.xinclude.sax;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.etourdot.xincproc.xinclude.exceptions.XIncludeFatalException;

/**
 * Internal class used to store doctype representation
 */
class DocType
{
    static DocType copy(final DocType docTypeToCopy)
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

    private void outputElements(final StringBuilder docTypeBuffer)
    {
        for (final DocType.Element element : this.elements)
        {
            docTypeBuffer.append("<!ELEMENT ").append(element.getName()).append(' ')
                    .append(element.getValue()).append('>');
            outputAttributes(docTypeBuffer, element);
        }
    }

    private void outputAttributes(final StringBuilder docTypeBuffer, final DocType.Element element)
    {
        final ImmutableList<DocType.Attribute> attributesList = this.attributes.get(element.getName());
        if (null != attributesList && !attributesList.isEmpty())
        {
            docTypeBuffer.append("<!ATTLIST ").append(element.getName()).append(' ');
            for (final DocType.Attribute attribute : attributesList)
            {
                docTypeBuffer.append(attribute.getaName())
                        .append(' ').append(attribute.getType()).append(' ').append(attribute.getMode())
                        .append(' ').append(Strings.nullToEmpty(attribute.getValue()));
            }
            docTypeBuffer.append('>');
        }
    }

    private void outputExternalEntities(final StringBuilder docTypeBuffer)
    {
        for (final DocType.ExternalEntity externalEntity : this.externalEntities)
        {
            docTypeBuffer.append("<!NOTATION ").append(externalEntity.getName()).append(' ')
                    .append(externalEntity.getPublicId()).append(' ').append(externalEntity.getSystemId())
                    .append('>');
        }
    }

    private void outputInternalEntities(final StringBuilder docTypeBuffer)
    {
        for (final DocType.InternalEntity internalEntity : this.internalEntities)
        {
            docTypeBuffer.append("<!ENTITY ").append(internalEntity.getName()).append(' ')
                    .append(internalEntity.getValue()).append('>');
        }
    }

    DocType setDoctype(final String doctype)
    {
        this.doctype = Optional.fromNullable(doctype);
        return this;
    }

    DocType setPublicId(final String publicId)
    {
        this.publicId = Optional.fromNullable(publicId);
        return this;
    }

    DocType setSystemId(final String systemId)
    {
        this.systemId = Optional.fromNullable(systemId);
        return this;
    }

    DocType addElement(final String name, final String model)
    {
        this.elements = new ImmutableList.Builder<DocType.Element>().addAll(this.elements).add(new DocType.Element(name, model)).build();
        return this;
    }

    DocType addAttribute(final String eName, final String aName, final String type, final String mode,
                                final String value)
    {
        final DocType.Attribute newAttribute = new DocType.Attribute(aName, type, mode, value);
        final ImmutableList.Builder<DocType.Attribute> listAttributeBuilder = new ImmutableList.Builder<DocType.Attribute>();
        if (null != this.attributes.get(eName))
        {
            listAttributeBuilder.addAll(this.attributes.get(eName));
        }
        listAttributeBuilder.add(newAttribute);
        this.attributes = new ImmutableMap.Builder<String, ImmutableList<DocType.Attribute>>().put(eName,
                listAttributeBuilder.build()).build();
        return this;
    }

    DocType addExternalEntity(final String name, final String publicId, final String systemId)
    {
        this.externalEntities = new ImmutableList.Builder<DocType.ExternalEntity>().addAll(this.externalEntities)
                .add(new DocType.ExternalEntity(name, publicId, systemId)).build();
        return this;
    }

    DocType addInternalEntity(final String name, final String value)
    {
        this.internalEntities = new ImmutableList.Builder<DocType.InternalEntity>().addAll(this.internalEntities)
                .add(new DocType.InternalEntity(name, value)).build();
        return this;
    }

    DocType addUnparsedEntity(final String name, final String publicId, final String systemId,
                                     final String notationName)
            throws XIncludeFatalException
    {
        final DocType.UnparsedEntity unparsedEntity = this.unparsedEntities.get(name);
        if (null != unparsedEntity && null != systemId && !systemId.equals(unparsedEntity.getSystemId()))
        {
            throw new XIncludeFatalException("duplicate unparsed entity");
        }
        this.unparsedEntities = new ImmutableMap.Builder<String, DocType.UnparsedEntity>().put(name,
                new DocType.UnparsedEntity(notationName, publicId, systemId)).build();
        return this;
    }

    String getDocTypeValue()
    {
        final StringBuilder docTypeBuffer = new StringBuilder();
        if (this.doctype.isPresent())
        {
            docTypeBuffer.append("<!DOCTYPE ").append(this.doctype.get());
            if (this.systemId.isPresent())
            {
                if (this.publicId.isPresent())
                {
                    docTypeBuffer.append("PUBLIC \"").append(this.publicId.get()).append("\" \"")
                            .append(this.systemId.get()).append('"');
                }
                else
                {
                    docTypeBuffer.append("SYSTEM \"").append(this.systemId.get()).append('"');
                }
            }
            if (!this.elements.isEmpty())
            {
                docTypeBuffer.append(" [");
                outputElements(docTypeBuffer);
                outputInternalEntities(docTypeBuffer);
                outputExternalEntities(docTypeBuffer);
                docTypeBuffer.append(']');
            }
            docTypeBuffer.append('>');
        }
        return docTypeBuffer.toString();
    }

    private static final class Element
    {
        private Element(final String name, final String value)
        {
            this.name = name;
            this.value = value;
        }

        String getName()
        {
            return this.name;
        }

        String getValue()
        {
            return this.value;
        }
        private final String name;
        private final String value;
    }

    private static final class ExternalEntity
    {
        private ExternalEntity(final String name, final String publicId, final String systemId)
        {
            this.name = name;
            this.publicId = publicId;
            this.systemId = systemId;
        }

        String getName()
        {
            return this.name;
        }

        String getPublicId()
        {
            return this.publicId;
        }

        String getSystemId()
        {
            return this.systemId;
        }
        private final String name;
        private final String publicId;
        private final String systemId;
    }

    private static final class InternalEntity
    {
        private InternalEntity(final String name, final String value)
        {
            this.name = name;
            this.value = value;
        }

        String getName()
        {
            return this.name;
        }

        String getValue()
        {
            return this.value;
        }
        private final String name;
        private final String value;
    }

    private static final class Attribute
    {
        private Attribute(final String aName, final String type, final String mode, final String value)
        {
            this.aName = aName;
            this.type = type;
            this.mode = mode;
            this.value = value;
        }

        String getaName()
        {
            return this.aName;
        }

        String getMode()
        {
            return this.mode;
        }

        String getType()
        {
            return this.type;
        }

        String getValue()
        {
            return this.value;
        }
        private final String aName;
        private final String type;
        private final String mode;
        private final String value;
    }

    private static final class UnparsedEntity
    {
        private UnparsedEntity(final String notationName, final String publicId, final String systemId)
        {
            this.notationName = notationName;
            this.publicId = publicId;
            this.systemId = systemId;
        }

        String getSystemId()
        {
            return this.systemId;
        }
        private final String publicId;
        private final String systemId;
        private final String notationName;
    }

    private Optional<String> doctype = Optional.absent();
    private Optional<String> publicId = Optional.absent();
    private Optional<String> systemId = Optional.absent();
    private ImmutableList<DocType.Element> elements = ImmutableList.of();
    private ImmutableMap<String, ImmutableList<DocType.Attribute>> attributes = ImmutableMap.of();
    private ImmutableList<DocType.ExternalEntity> externalEntities = ImmutableList.of();
    private ImmutableList<DocType.InternalEntity> internalEntities = ImmutableList.of();
    private ImmutableMap<String, DocType.UnparsedEntity> unparsedEntities = ImmutableMap.of();
}
