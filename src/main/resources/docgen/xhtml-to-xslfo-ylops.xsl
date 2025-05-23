<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml"/>
    <xsl:param name="page-size" select="'a4'"/>
    <xsl:param name="docgenPath"/>

    <xsl:template match="html">

        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <xsl:choose>
                    <xsl:when test="$page-size='a4'">

                        <fo:simple-page-master master-name="cover"
                                               page-width="210mm" page-height="297mm">
                            <fo:region-body margin="30mm" margin-top="80mm"/>
                        </fo:simple-page-master>

                        <!-- Just a blank A4 -->
                        <fo:simple-page-master master-name="blank"
                                               page-width="210mm" page-height="297mm"
                                               margin="30mm">
                            <fo:region-body/>
                        </fo:simple-page-master>

                        <!-- Left side page -->
                        <fo:simple-page-master master-name="left"
                                               page-width="210mm" page-height="297mm">
                            <fo:region-body margin-top="20mm"
                                            margin-bottom="20mm"
                                            margin-left="30mm"
                                            margin-right="30mm"/>
                            <fo:region-before region-name="rb-left"
                                              extent="20mm"/>
                            <fo:region-after region-name="ra-left"
                                             extent="20mm"/>
                            <fo:region-start region-name="rs-left" extent="30mm"
                                             reference-orientation="90" display-align="before"
                                             background-image="{$docgenPath}gradient.svg"
                                             background-repeat="no-repeat" background-position-horizontal="left"/>
                            <fo:region-end extent="30mm"/>
                        </fo:simple-page-master>

                        <!-- Right side page -->
                        <fo:simple-page-master master-name="right"
                                               page-width="210mm" page-height="297mm">
                            <fo:region-body margin-top="20mm"
                                            margin-bottom="20mm"
                                            margin-left="30mm"
                                            margin-right="30mm"/>
                            <fo:region-before region-name="rb-right"
                                              extent="20mm"/>
                            <fo:region-after region-name="ra-right"
                                             extent="20mm"/>
                            <fo:region-start extent="30mm"/>
                            <fo:region-end region-name="re-right" extent="30mm"
                                           reference-orientation="90" display-align="after"
                                           background-image="{$docgenPath}gradient.svg"
                                           background-repeat="no-repeat" background-position-horizontal="right"/>
                        </fo:simple-page-master>
                    </xsl:when>
                </xsl:choose>

                <!-- Page layout using rules -->
                <fo:page-sequence-master master-name="standard">
                    <fo:repeatable-page-master-alternatives>
                        <fo:conditional-page-master-reference
                                master-reference="left"
                                odd-or-even="even"/>
                        <fo:conditional-page-master-reference
                                master-reference="right"
                                odd-or-even="odd"/>
                    </fo:repeatable-page-master-alternatives>
                </fo:page-sequence-master>

            </fo:layout-master-set>

            <!-- Bookmarks -->
            <xsl:call-template name="generate-bookmarks"/>

            <!-- Cover page -->
            <fo:page-sequence master-reference="cover">
                <fo:flow flow-name="xsl-region-body">
                    <xsl:call-template name="cover"/>
                </fo:flow>
            </fo:page-sequence>

            <!-- Head page -->
            <fo:page-sequence master-reference="blank">
                <fo:flow flow-name="xsl-region-body">
                    <xsl:apply-templates select="head"/>
                </fo:flow>
            </fo:page-sequence>

            <!-- Table of contents pages -->
            <fo:page-sequence master-reference="blank">
                <fo:flow flow-name="xsl-region-body">
                    <xsl:call-template name="toc"/>
                </fo:flow>
            </fo:page-sequence>

            <!-- Document content pages -->
            <fo:page-sequence master-reference="standard" initial-page-number="1">

                <fo:static-content flow-name="rb-right">
                    <fo:block font-size="10pt" text-align="start"/>
                    <xsl:apply-templates select="/html/head/ylatunniste"/>
                </fo:static-content>
                <fo:static-content flow-name="ra-right">
                    <fo:block text-align="end" font-size="10pt" color="#6C6D70">
                        <fo:page-number/>
                    </fo:block>
                    <xsl:apply-templates select="/html/head/alatunniste"/>
                </fo:static-content>
                <fo:static-content flow-name="rb-left">
                    <fo:block font-size="10pt" text-align="end"/>
                    <xsl:apply-templates select="/html/head/ylatunniste"/>
                </fo:static-content>
                <fo:static-content flow-name="ra-left">
                    <fo:block font-size="10pt" color="#6C6D70">
                        <fo:page-number/>
                    </fo:block>
                    <xsl:apply-templates select="/html/head/alatunniste"/>
                </fo:static-content>
                <fo:static-content flow-name="rs-left">
                    <fo:block text-align="start" color="#007ec5" margin-left="20mm"
                              margin-top="5mm" display-align="before">
                        <fo:retrieve-marker retrieve-class-name="chapter"/>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="re-right">
                    <fo:block text-align="start" color="#007ec5" margin-left="20mm"
                              margin-bottom="5mm" display-align="after">
                        <fo:retrieve-marker retrieve-class-name="chapter"/>
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-footnote-separator">
                    <fo:block text-align-last="justify">
                        <fo:leader leader-pattern="rule" leader-length.maximum="100%" leader-length.optimum="100%"
                                   rule-thickness="1pt" color="#6E6C6C"/>
                    </fo:block>
                </fo:static-content>
                <xsl:apply-templates select="body"/>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="head">
        <fo:table table-layout="fixed" width="100%" font-size="10pt"
                  border-collapse="separate" border-separation="4pt">
            <fo:table-column column-width="proportional-column-width(1)"/>
            <fo:table-column column-width="proportional-column-width(2)"/>
            <fo:table-body>

                <!-- nimi -->
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            <xsl:if test="//html/@lang='fi'">
                                <xsl:text>Nimi</xsl:text>
                            </xsl:if>
                            <xsl:if test="//html/@lang='sv'">
                                <xsl:text>Namn</xsl:text>
                            </xsl:if>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:value-of select="title"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

                <!-- Tiivistelmä -->
                <xsl:if test="boolean(/html/head/meta[@name='description'])">
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-weight="bold">
                                <xsl:if test="//html/@lang='fi'">
                                    <xsl:text>Tiivistelmä</xsl:text>
                                </xsl:if>
                                <xsl:if test="//html/@lang='sv'">
                                    <xsl:text>Sammandrag</xsl:text>
                                </xsl:if>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block>
                                <xsl:apply-templates select="/html/head/meta[@name='description']/@content"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:if>

                <xsl:apply-templates select="kunnat"/>

                <xsl:apply-templates select="organisaatiot"/>

                <xsl:apply-templates select="koulut"/>

                <!-- Hyväksymispäivämäärä -->
                <xsl:if test="boolean(/html/head/meta[@name='date'])">
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-weight="bold">
                                <xsl:if test="//html/@lang='fi'">
                                    <xsl:text>Hyväksymispäivämäärä</xsl:text>
                                </xsl:if>
                                <xsl:if test="//html/@lang='sv'">
                                    <xsl:text>Träder i kraft</xsl:text>
                                </xsl:if>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block>
                                <xsl:apply-templates select="/html/head/meta[@name='date']/@content"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:if>

                <xsl:if test="boolean(/html/head/meta[@name='pdfluotu'])">
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-weight="bold">
                                <xsl:apply-templates select="/html/head/meta[@name='pdfluotu']/@translate"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block>
                                <xsl:apply-templates select="/html/head/meta[@name='pdfluotu']/@content"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:if>

                <fo:table-row>
                    <fo:table-cell>
                        <fo:block font-weight="bold">
                            <xsl:apply-templates select="/html/head/meta[@name='pdfjulkaistu']/@translate"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block>
                            <xsl:apply-templates select="/html/head/meta[@name='pdfjulkaistu']/@content"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template match="body">
        <fo:flow flow-name="xsl-region-body">
            <xsl:choose>
                <xsl:when test="*|text()">
                    <xsl:apply-templates select="*|text()"/>
                </xsl:when>
                <xsl:otherwise>
                    <fo:block>
                        <xsl:text>Content is missing</xsl:text>
                    </fo:block>
                </xsl:otherwise>
            </xsl:choose>
        </fo:flow>
    </xsl:template>

    <xsl:template match="h1">
        <fo:block font-size="20pt" line-height="1.25em" font-weight="bold" break-before="page"
                  keep-with-next="always" space-after="16pt" color="#007EC5">

            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:when test="name(preceding-sibling::*[1])='a' and
                          preceding-sibling::*[1][@name]">
                        <xsl:value-of select="preceding-sibling::*[1]/@name"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <fo:marker marker-class-name="chapter">
                <xsl:value-of select="*|text()"/>
            </fo:marker>

            <xsl:choose>
                <xsl:when test="@number">
                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="20mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:apply-templates select="*|text()"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="*|text()"/>
                </xsl:otherwise>
            </xsl:choose>
        </fo:block>
    </xsl:template>

    <xsl:template match="h2">
        <fo:block font-size="16pt" line-height="1.25em" font-weight="bold"
                  keep-with-next="always" space-after="10pt" color="#007EC5">

            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <xsl:choose>
                <xsl:when test="@number">
                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="20mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:apply-templates select="*|text()"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="*|text()"/>
                </xsl:otherwise>
            </xsl:choose>
        </fo:block>
    </xsl:template>


    <xsl:template match="h3">
        <fo:block font-size="14pt" line-height="1.25em" font-weight="bold"
                  keep-with-next="always" space-after="10pt" color="#007EC5">

            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <xsl:choose>
                <xsl:when test="@number and @showHeaderNumber='true'">
                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="20mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:apply-templates select="*|text()"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="*|text()"/>
                </xsl:otherwise>
            </xsl:choose>
        </fo:block>
    </xsl:template>

    <xsl:template match="h4">
        <fo:block font-size="12pt" line-height="1.25em" font-weight="bold"
                  keep-with-next="always" space-after="10pt" color="#007EC5">
            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <xsl:choose>
                <xsl:when test="@number and @showHeaderNumber='true'">
                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="20mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:apply-templates select="*|text()"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="*|text()"/>
                </xsl:otherwise>
            </xsl:choose>

        </fo:block>
    </xsl:template>

    <xsl:template match="h5">
        <fo:block font-size="12pt" line-height="1.25em" font-weight="bold"
                  keep-with-next="always" space-after="10pt" color="#007EC5">
            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <xsl:choose>
                <xsl:when test="@number and @showHeaderNumber='true'">
                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="20mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:apply-templates select="*|text()"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="*|text()"/>
                </xsl:otherwise>
            </xsl:choose>

        </fo:block>
    </xsl:template>

    <xsl:template match="h5i">
        <fo:block font-size="12pt" line-height="1.25em" font-weight="bold" font-style="italic"
                  keep-with-next="always" space-after="10pt" color="#007EC5">
            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>

            <xsl:choose>
                <xsl:when test="@number and @showHeaderNumber='true'">
                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="20mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:apply-templates select="*|text()"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="*|text()"/>
                </xsl:otherwise>
            </xsl:choose>

        </fo:block>
    </xsl:template>

    <xsl:template match="h6">
        <fo:block font-size="10pt" line-height="1.25em" font-weight="bold"
                  keep-with-next="always" space-after="10pt" color="#000000">
            <xsl:attribute name="id">
                <xsl:choose>
                    <xsl:when test="@id">
                        <xsl:value-of select="@id"/>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="generate-id()"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="tavoitteet-otsikko">
        <fo:block font-size="12pt" line-height="1.25em" font-weight="bold"
                  keep-with-next="always" space-after="10pt" color="#000000">
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="a">
        <xsl:choose>
            <xsl:when test="@name">
                <xsl:if test="not(name(following-sibling::*[1])='h1')">
                    <fo:block line-height="0pt" space-after="0pt"
                              font-size="0pt" id="{@name}"/>
                </xsl:if>
            </xsl:when>
            <xsl:when test="@href and @href != '' and starts-with(@href,'#') and string-length(@href) &gt; 1">
                <fo:basic-link color="red">
                    <xsl:attribute name="internal-destination">
                        <xsl:value-of select="substring-after(@href,'#')"/>
                    </xsl:attribute>
                    <xsl:apply-templates select="*|text()"/>
                </fo:basic-link>
            </xsl:when>
            <xsl:when test="@href and @href != '' and not(starts-with(@href,'['))">
                <fo:basic-link color="blue">
                    <xsl:attribute name="external-destination">
                        <xsl:value-of select="@href"/>
                    </xsl:attribute>
                    <xsl:apply-templates select="*|text()"/>
                </fo:basic-link>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="blockquote">
        <fo:block start-indent="1.5cm" end-indent="1.5cm"
                  space-after="12pt">
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="br">
        <fo:block> </fo:block>
    </xsl:template>

    <xsl:template match="em">
        <fo:inline font-style="italic">
            <xsl:apply-templates select="*|text()"/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="hr">
        <fo:block>
            <fo:leader leader-pattern="rule" leader-length.maximum="100%" leader-length.optimum="100%"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="img">
        <xsl:if test="@src">
            <fo:block space-after="12pt" text-align="center">
                <fo:table table-layout="fixed" width="100%">
                    <fo:table-column column-width="proportional-column-width(1)"/>
                    <fo:table-body>
                        <fo:table-row keep-with-next="always">
                            <fo:table-cell>
                                <fo:block>
                                    <xsl:choose>
                                        <xsl:when test="@tyyppi='kansi'">
                                            <fo:external-graphic src="{@src}" content-height="100mm" content-width="scale-to-fit"
                                                                 width="100%" scaling="uniform"/>
                                        </xsl:when>
                                        <xsl:when test="@tyyppi='tunniste'">
                                            <fo:external-graphic src="{@src}" content-height="15mm" content-width="scale-to-fit"
                                                                 width="100%" scaling="uniform"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <fo:external-graphic src="{@src}" content-width="scale-to-fit" content-height="100%"
                                                                 width="100%" scaling="uniform"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell>
                                <xsl:choose>
                                    <xsl:when test="@figcaption and not(@figcaption = '')">
                                        <fo:block>
                                            <xsl:value-of select="@figcaption"/>
                                        </fo:block>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:if test="not(@alt = 'undefined')">
                                            <fo:block>
                                                <xsl:value-of select="@alt"/>
                                            </fo:block>
                                        </xsl:if>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </fo:table-cell>
                        </fo:table-row>
                    </fo:table-body>
                </fo:table>
            </fo:block>
        </xsl:if>
    </xsl:template>

    <xsl:template match="ol">
        <xsl:if test="li">
            <fo:list-block provisional-distance-between-starts="1cm"
                           provisional-label-separation="0.5cm">
                <xsl:attribute name="space-after">
                    <xsl:choose>
                        <xsl:when test="ancestor::ul or ancestor::ol">
                            <xsl:text>0pt</xsl:text>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:text>10pt</xsl:text>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:attribute>
                <xsl:attribute name="start-indent">
                    <xsl:variable name="ancestors">
                        <xsl:choose>
                            <xsl:when test="count(ancestor::ol) or boolean(count(ancestor::ul))">
                                <xsl:value-of select="1 +
                                    (count(ancestor::ol) +
                                     count(ancestor::ul)) *
                                    1.25"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>1</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>
                    <xsl:value-of select="concat($ancestors, 'cm')"/>
                </xsl:attribute>
                <xsl:apply-templates select="*"/>
            </fo:list-block>
        </xsl:if>
    </xsl:template>


    <xsl:template match="ol/li">
        <fo:list-item>
            <fo:list-item-label end-indent="label-end()">
                <fo:block>
                    <xsl:variable name="value-attr">
                        <xsl:choose>
                            <xsl:when test="../@start">
                                <xsl:number value="position() + number(../@start) - 1"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:number value="position()"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>
                    <xsl:choose>
                        <xsl:when test="../@type='i'">
                            <xsl:number value="$value-attr" format="i. "/>
                        </xsl:when>
                        <xsl:when test="../@type='I'">
                            <xsl:number value="$value-attr" format="I. "/>
                        </xsl:when>
                        <xsl:when test="../@type='a'">
                            <xsl:number value="$value-attr" format="a. "/>
                        </xsl:when>
                        <xsl:when test="../@type='A'">
                            <xsl:number value="$value-attr" format="A. "/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:number value="$value-attr" format="1. "/>
                        </xsl:otherwise>
                    </xsl:choose>
                </fo:block>
            </fo:list-item-label>
            <fo:list-item-body start-indent="body-start()">
                <fo:block font-size="10pt">
                    <xsl:apply-templates select="*|text()"/>
                </fo:block>
            </fo:list-item-body>
        </fo:list-item>
    </xsl:template>

    <xsl:template match="p">
        <fo:block font-size="10pt" line-height="1.25em"
                  space-after="0.75em" text-align="justify">
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>


    <xsl:template match="div">
        <fo:block font-size="10pt" line-height="1.25em"
                  space-after="20pt" text-align="justify">
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="abbr">

        <xsl:value-of select="."/>

        <!-- Show endnotes bottom of the page -->
        <fo:footnote>
            <fo:inline baseline-shift="4pt" font-size="8pt">
                <xsl:value-of select="@number"/>
            </fo:inline>
            <fo:footnote-body>
                <fo:block font-size="8pt" line-height="10pt" start-indent="0" text-align="left" color="black">

                    <fo:table table-layout="fixed" width="100%">
                        <fo:table-column column-width="10mm"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="center">
                                        <xsl:value-of select="@number"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <xsl:value-of select="@text"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                </fo:block>
            </fo:footnote-body>
        </fo:footnote>
    </xsl:template>

    <xsl:template match="pre">
        <fo:block font-family="monospace"
                  white-space-collapse="false" wrap-option="no-wrap"
                  linefeed-treatment="preserve" white-space-treatment="preserve">
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>

    <xsl:template match="strong">
        <fo:inline font-weight="bold">
            <xsl:apply-templates select="*|text()"/>
        </fo:inline>
    </xsl:template>

    <xsl:template match="table">
        <fo:table table-layout="fixed" inline-progression-dimension="100%"
                  space-after="12pt" font-size="10pt"
                  page-break-inside="avoid" keep-together.within-column="1">
            <xsl:if test="caption">
                <fo:table-header>
                    <fo:table-cell>
                        <fo:block font-weight="bold" font-size="12pt" text-align="center">
                            <xsl:value-of select="caption"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-header>
            </xsl:if>
            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                </fo:table-row>
                <xsl:apply-templates select="thead|tbody|tr"/>
            </fo:table-body>
        </fo:table>
    </xsl:template>

    <xsl:template match="td">
        <fo:table-cell
                padding-start="3pt" padding-end="3pt"
                padding-before="3pt" padding-after="3pt">
            <xsl:if test="not(table[@border='1']) and not(table[@border='0'])">
                <xsl:attribute name="border-style">
                    <xsl:text>solid</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-color">
                    <xsl:text>#ddd</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-width">
                    <xsl:text>1pt</xsl:text>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@colspan">
                <xsl:attribute name="number-columns-spanned">
                    <xsl:value-of select="@colspan"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
                <xsl:attribute name="number-rows-spanned">
                    <xsl:value-of select="@rowspan"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@border='1' or
                    ancestor::tr[@border='1'] or
                    ancestor::tbody[@border='1'] or
                    ancestor::thead[@border='1'] or
                    ancestor::table[@border='1']">
                <xsl:attribute name="border-style">
                    <xsl:text>solid</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-color">
                    <xsl:text>black</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-width">
                    <xsl:text>1pt</xsl:text>
                </xsl:attribute>
            </xsl:if>
            <xsl:variable name="align">
                <xsl:choose>
                    <xsl:when test="@align">
                        <xsl:choose>
                            <xsl:when test="@align='center'">
                                <xsl:text>center</xsl:text>
                            </xsl:when>
                            <xsl:when test="@align='right'">
                                <xsl:text>end</xsl:text>
                            </xsl:when>
                            <xsl:when test="@align='justify'">
                                <xsl:text>justify</xsl:text>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>start</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:when>
                    <xsl:when test="ancestor::tr[@align]">
                        <xsl:choose>
                            <xsl:when test="ancestor::tr/@align='center'">
                                <xsl:text>center</xsl:text>
                            </xsl:when>
                            <xsl:when test="ancestor::tr/@align='right'">
                                <xsl:text>end</xsl:text>
                            </xsl:when>
                            <xsl:when test="ancestor::tr/@align='justify'">
                                <xsl:text>justify</xsl:text>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>start</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:when>
                    <xsl:when test="ancestor::thead">
                        <xsl:text>center</xsl:text>
                    </xsl:when>
                    <xsl:when test="ancestor::table[@align]">
                        <xsl:choose>
                            <xsl:when test="ancestor::table/@align='center'">
                                <xsl:text>center</xsl:text>
                            </xsl:when>
                            <xsl:when test="ancestor::table/@align='right'">
                                <xsl:text>end</xsl:text>
                            </xsl:when>
                            <xsl:when test="ancestor::table/@align='justify'">
                                <xsl:text>justify</xsl:text>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>start</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:text>start</xsl:text>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:variable>
            <fo:block text-align="{$align}">
                <xsl:apply-templates select="*|text()"/>
            </fo:block>
        </fo:table-cell>
    </xsl:template>

    <xsl:template match="th">
        <fo:table-cell
                padding-start="3pt" padding-end="3pt"
                padding-before="3pt" padding-after="3pt">
            <xsl:if test="not(table[@border='1']) and not(table[@border='0'])">
                <xsl:attribute name="border-style">
                    <xsl:text>solid</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-color">
                    <xsl:text>#ddd</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-width">
                    <xsl:text>1pt</xsl:text>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@colspan">
                <xsl:attribute name="number-columns-spanned">
                    <xsl:value-of select="@colspan"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
                <xsl:attribute name="number-rows-spanned">
                    <xsl:value-of select="@rowspan"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="@border='1' or
                    ancestor::tr[@border='1'] or
                    ancestor::tbody[@border='1'] or
                    ancestor::thead[@border='1'] or
                    ancestor::table[@border='1']">
                <xsl:attribute name="border-style">
                    <xsl:text>solid</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-color">
                    <xsl:text>black</xsl:text>
                </xsl:attribute>
                <xsl:attribute name="border-width">
                    <xsl:text>1pt</xsl:text>
                </xsl:attribute>
            </xsl:if>
            <fo:block font-weight="bold" text-align="center">
                <xsl:apply-templates select="*|text()"/>
            </fo:block>
        </fo:table-cell>
    </xsl:template>

    <xsl:template match="thead">
        <xsl:apply-templates select="tr"/>
    </xsl:template>

    <xsl:template match="tbody">
        <xsl:apply-templates select="tr"/>
    </xsl:template>

    <xsl:template match="tr">
        <fo:table-row>
            <xsl:choose>
                <xsl:when test="th|td">
                    <xsl:apply-templates select="th|td"/>
                </xsl:when>
                <xsl:otherwise>
                    <fo:table-cell>
                        <fo:block/>
                    </fo:table-cell>
                </xsl:otherwise>
            </xsl:choose>
        </fo:table-row>
    </xsl:template>

    <xsl:template match="ul">
        <xsl:if test="li">
            <fo:list-block provisional-distance-between-starts="0.5cm"
                           provisional-label-separation="0.25cm">
                <xsl:attribute name="space-after">
                    <xsl:choose>
                        <xsl:when test="ancestor::ul or ancestor::ol">
                            <xsl:text>0pt</xsl:text>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:text>10pt</xsl:text>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:attribute>
                <xsl:attribute name="start-indent">
                    <xsl:variable name="ancestors">
                        <xsl:choose>
                            <xsl:when test="count(ancestor::ol) or boolean(count(ancestor::ul))">
                                <xsl:value-of select="0.5 +
                                        (count(ancestor::ol) +
                                         count(ancestor::ul)) *
                                        0.75"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>0.5</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:variable>
                    <xsl:value-of select="concat($ancestors, 'cm')"/>
                </xsl:attribute>
                <xsl:apply-templates select="*"/>
            </fo:list-block>
        </xsl:if>
    </xsl:template>

    <xsl:template match="ul/li">
        <fo:list-item>
            <fo:list-item-label end-indent="label-end()">
                <fo:block>&#x2022;</fo:block>
            </fo:list-item-label>
            <fo:list-item-body start-indent="body-start()">
                <fo:block font-size="10pt">
                    <xsl:apply-templates select="*|text()"/>
                </fo:block>
            </fo:list-item-body>
        </fo:list-item>
    </xsl:template>

    <!-- Custom tags -->

    <xsl:template match="kunnat">
        <xsl:for-each select="kunta">
            <fo:table-row>
                <fo:table-cell>
                    <fo:block font-weight="bold">
                        <xsl:if test="position()=1">
                            <xsl:if test="//html/@lang='fi'">
                                <xsl:text>Kunta</xsl:text>
                            </xsl:if>
                            <xsl:if test="//html/@lang='sv'">
                                <xsl:text>Kommun</xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block>
                        <xsl:apply-templates select="."/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="organisaatiot">
        <xsl:for-each select="organisaatio">
            <fo:table-row>
                <fo:table-cell>
                    <fo:block font-weight="bold">
                        <xsl:if test="position()=1">
                            <xsl:if test="//html/@lang='fi'">
                                <xsl:text>Organisaatio</xsl:text>
                            </xsl:if>
                            <xsl:if test="//html/@lang='sv'">
                                <xsl:text>Organisation</xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block>
                        <xsl:apply-templates select="."/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="koulut">
        <xsl:for-each select="koulu">
            <fo:table-row>
                <fo:table-cell>
                    <fo:block font-weight="bold">
                        <xsl:if test="position()=1">
                            <xsl:if test="//html/@lang='fi'">
                                <xsl:text>Koulu</xsl:text>
                            </xsl:if>
                            <xsl:if test="//html/@lang='sv'">
                                <xsl:text>Skola</xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block>
                        <xsl:apply-templates select="."/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="cite">
        <fo:block color="#444444" font-style="italic" font-size="10pt"
                  space-after="20pt" text-align="justify" line-height="1.25em">
            <xsl:apply-templates select="*|text()"/>
        </fo:block>
    </xsl:template>

    <!-- Named templates -->

    <!-- Cover page -->
    <xsl:template name="cover">
        <fo:block font-weight="bold" font-size="28pt" text-align="center">
            <xsl:value-of select="/html/head/title"/>
            <xsl:apply-templates select="/html/head/kansikuva"/>
        </fo:block>
    </xsl:template>

    <xsl:template name="toc">
        <fo:block break-before="page" space-after="20pt" id="TableOfContents" color="#007EC5" font-weight="bold"
                  line-height="20pt" font-size="18pt" text-align="start">
            <xsl:if test="//html/@lang='fi'">
                <xsl:text>SISÄLTÖ</xsl:text>
            </xsl:if>
            <xsl:if test="//html/@lang='sv'">
                <xsl:text>Innehållsförteckning</xsl:text>
            </xsl:if>
        </fo:block>

        <xsl:for-each select="
        /html/body//h1 |
        /html/body//h2 |
        /html/body//h3 |
        /html/body//h4 |
        /html/body//h5">
            <xsl:if test="@showInMenu='true'">
                <fo:block text-align-last="justify" font-size="12pt"
                          space-after="0.25em" text-align="start" text-indent="-1cm">


                    <xsl:attribute name="start-indent">
                        <xsl:choose>
                            <xsl:when test="name()='h1'">
                                <xsl:text>1cm</xsl:text>
                            </xsl:when>
                            <xsl:when test="name()='h2'">
                                <xsl:text>1.5cm</xsl:text>
                            </xsl:when>
                            <xsl:when test="name()='h3'">
                                <xsl:text>2cm</xsl:text>
                            </xsl:when>
                            <xsl:when test="name()='h4'">
                                <xsl:text>2.5cm</xsl:text>
                            </xsl:when>
                            <xsl:when test="name()='h5'">
                                <xsl:text>3cm</xsl:text>
                            </xsl:when>
                        </xsl:choose>
                    </xsl:attribute>

                    <xsl:if test="name()='h1'">
                        <xsl:attribute name="color">
                            <xsl:text>#007EC5</xsl:text>
                        </xsl:attribute>
                        <xsl:attribute name="space-before">
                            <xsl:text>0.5em</xsl:text>
                        </xsl:attribute>
                    </xsl:if>

                    <fo:basic-link>
                        <xsl:attribute name="internal-destination">
                            <xsl:choose>
                                <xsl:when test="@id">
                                    <xsl:value-of select="@id"/>
                                </xsl:when>
                                <xsl:when test="name(preceding-sibling::*[1])='a' and
                                  preceding-sibling::*[1][@name]">
                                    <xsl:value-of select="preceding-sibling::*[1]/@name"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="generate-id()"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:attribute>

                        <xsl:if test="@number">
                            <xsl:if test="@showHeaderNumber='true'">
                                <xsl:value-of select="@number"/>
                                <xsl:text> </xsl:text>
                            </xsl:if>
                        </xsl:if>
                        <xsl:apply-templates select="*|text()"/>
                    </fo:basic-link>

                    <fo:leader leader-pattern="dots" leader-pattern-width="8pt"/>

                    <fo:page-number-citation>
                        <xsl:attribute name="ref-id">
                            <xsl:choose>
                                <xsl:when test="@id">
                                    <xsl:value-of select="@id"/>
                                </xsl:when>
                                <xsl:when test="name(preceding-sibling::*[1])='a' and
                                  preceding-sibling::*[1][@name]">
                                    <xsl:value-of select="preceding-sibling::*[1]/@name"/>
                                </xsl:when>
                                <xsl:otherwise>
                                    <xsl:value-of select="generate-id()"/>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:attribute>
                    </fo:page-number-citation>

                </fo:block>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="generate-bookmarks">
        <fo:bookmark-tree>
            <fo:bookmark internal-destination="TableOfContents">
                <fo:bookmark-title>Sisältö</fo:bookmark-title>
            </fo:bookmark>
            <xsl:for-each select="/html/body//h1">
                <xsl:variable name="current-h1" select="generate-id()"/>
                <fo:bookmark starting-state="hide">
                    <xsl:attribute name="internal-destination">
                        <xsl:choose>
                            <xsl:when test="@id">
                                <xsl:value-of select="@id"/>
                            </xsl:when>
                            <xsl:when test="name(preceding-sibling::*[1])='a' and
                              preceding-sibling::*[1][@name]">
                                <xsl:value-of select="preceding-sibling::*[1]/@name"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:value-of select="$current-h1"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:attribute>
                    <fo:bookmark-title>
                        <xsl:value-of select="."/>
                    </fo:bookmark-title>
                    <xsl:for-each select="following-sibling::h2">
                        <xsl:variable name="current-h2" select="generate-id()"/>
                        <xsl:if
                                test="generate-id(preceding-sibling::h1[1])=$current-h1">
                            <fo:bookmark starting-state="hide">
                                <xsl:attribute name="internal-destination">
                                    <xsl:choose>
                                        <xsl:when test="@id">
                                            <xsl:value-of select="@id"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:value-of select="$current-h2"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <fo:bookmark-title>
                                    <xsl:value-of select="."/>
                                </fo:bookmark-title>
                                <xsl:for-each select="following-sibling::h3">
                                    <xsl:variable name="current-h3" select="generate-id()"/>
                                    <xsl:if
                                            test="generate-id(preceding-sibling::h2[1])=$current-h2">
                                        <fo:bookmark starting-state="hide">
                                            <xsl:attribute name="internal-destination">
                                                <xsl:choose>
                                                    <xsl:when test="@id">
                                                        <xsl:value-of select="@id"/>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <xsl:value-of select="$current-h3"/>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                            </xsl:attribute>
                                            <fo:bookmark-title>
                                                <xsl:value-of select="."/>
                                            </fo:bookmark-title>
                                            <xsl:for-each select="following-sibling::h4">
                                                <xsl:if
                                                        test="generate-id(preceding-sibling::h3[1])=$current-h3">
                                                    <fo:bookmark starting-state="hide">
                                                        <xsl:attribute name="internal-destination">
                                                            <xsl:choose>
                                                                <xsl:when test="@id">
                                                                    <xsl:value-of select="@id"/>
                                                                </xsl:when>
                                                                <xsl:otherwise>
                                                                    <xsl:value-of select="generate-id()"/>
                                                                </xsl:otherwise>
                                                            </xsl:choose>
                                                        </xsl:attribute>
                                                        <fo:bookmark-title>
                                                            <xsl:value-of select="."/>
                                                        </fo:bookmark-title>
                                                    </fo:bookmark>
                                                </xsl:if>
                                            </xsl:for-each>
                                        </fo:bookmark>
                                    </xsl:if>
                                </xsl:for-each>
                            </fo:bookmark>
                        </xsl:if>
                    </xsl:for-each>
                </fo:bookmark>
            </xsl:for-each>
        </fo:bookmark-tree>
    </xsl:template>

    <xsl:template name="build-columns">
        <xsl:param name="cols"/>

        <xsl:if test="boolean(string-length(normalize-space($cols)))">
            <xsl:variable name="next-col">
                <xsl:value-of select="substring-before($cols, ' ')"/>
            </xsl:variable>
            <xsl:variable name="remaining-cols">
                <xsl:value-of select="substring-after($cols, ' ')"/>
            </xsl:variable>
            <xsl:choose>
                <xsl:when test="contains($next-col, 'pt')">
                    <fo:table-column column-width="{$next-col}"/>
                </xsl:when>
                <xsl:when test="number($next-col) &gt; 0">
                    <fo:table-column column-width="{concat($next-col, 'pt')}"/>
                </xsl:when>
                <xsl:otherwise>
                    <fo:table-column column-width="50pt"/>
                </xsl:otherwise>
            </xsl:choose>

            <xsl:call-template name="build-columns">
                <xsl:with-param name="cols" select="concat($remaining-cols, ' ')"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>
