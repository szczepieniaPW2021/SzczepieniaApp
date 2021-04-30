package pl.students.szczepieniaapp.network.data

object MockWebServiceResponses {

    val routeWarsawWroclaw: String = "{\"geocoded_waypoints\":[{\"geocoder_status\":\"OK\",\"place_id\":\"ChIJIQZkFPPMHkcRpQq8gdD-KIw\",\"types\":[\"establishment\",\"point_of_interest\",\"subway_station\",\"transit_station\"]},{\"geocoder_status\":\"OK\",\"place_id\":\"ChIJ39NWyW_CD0cRwB6Ldg46Dnw\",\"types\":[\"premise\"]}],\"routes\":[{\"bounds\":{\"northeast\":{\"lat\":52.2296534,\"lng\":21.0119156},\"southwest\":{\"lat\":51.0999106,\"lng\":16.9796216}},\"copyrights\":\"Mapdata©2021Google\",\"legs\":[{\"distance\":{\"text\":\"359km\",\"value\":358966},\"duration\":{\"text\":\"3hours39mins\",\"value\":13115},\"end_address\":\"Piłsudskiego91A,50-019Wrocław,Poland\",\"end_location\":{\"lat\":51.0999106,\"lng\":17.0337644},\"start_address\":\"Centrum,00-057Warszawa,Poland\",\"start_location\":{\"lat\":52.2295654,\"lng\":21.0119156},\"steps\":[{\"distance\":{\"text\":\"0.1km\",\"value\":135},\"duration\":{\"text\":\"1min\",\"value\":48},\"end_location\":{\"lat\":52.2299527,\"lng\":21.0112515},\"html_instructions\":\"Head<b>northeast</b>on<b>RondoRomanaDmowskiego</b>toward<b>Marszałkowska</b>\",\"polyline\":{\"points\":\"yax}Ho{f_C?CEOEOEKGOM?QBIBGDGDCBCBEDEHGH?PAD@N@N@N@JBJBHBH@D@BBFHH\"},\"start_location\":{\"lat\":52.2295654,\"lng\":21.0119156},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.4km\",\"value\":449},\"duration\":{\"text\":\"1min\",\"value\":59},\"end_location\":{\"lat\":52.2285204,\"lng\":21.0051058},\"html_instructions\":\"Exittheroundaboutonto<b>Al.Jerozolimskie</b>/<wbr/><b>DW631</b>\",\"polyline\":{\"points\":\"edx}Hiwf_CJb@JVRp@Nn@F\\\\F\\\\N`Al@~Ef@~DRfBP|AJ~@L`AVhBJv@Fd@\"},\"start_location\":{\"lat\":52.2299527,\"lng\":21.0112515},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.2km\",\"value\":213},\"duration\":{\"text\":\"1min\",\"value\":40},\"end_location\":{\"lat\":52.2279632,\"lng\":21.0021163},\"html_instructions\":\"Continuestraighttostayon<b>Al.Jerozolimskie</b>/<wbr/><b>DW631</b>\",\"maneuver\":\"straight\",\"polyline\":{\"points\":\"g{w}H}pe_CXbCb@lDDV@RBP@L?B?PBPHp@T|B\"},\"start_location\":{\"lat\":52.2285204,\"lng\":21.0051058},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.9km\",\"value\":936},\"duration\":{\"text\":\"2mins\",\"value\":141},\"end_location\":{\"lat\":52.2251451,\"lng\":20.9891823},\"html_instructions\":\"Attheroundabout,takethe<b>1st</b>exitandstayon<b>Al.Jerozolimskie</b>/<wbr/><b>DW631</b>\",\"maneuver\":\"roundabout-right\",\"polyline\":{\"points\":\"www}Hg~d_C@PBN@B@L@B@H@DBNBNBL?@P|@\\\\dBBLHb@Hr@t@vFBVd@nDZfCHr@R|A\\\\pCBT`AxHhApID^|@~G\\\\rCb@vD\"},\"start_location\":{\"lat\":52.2279632,\"lng\":21.0021163},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"6.2km\",\"value\":6186},\"duration\":{\"text\":\"7mins\",\"value\":404},\"end_location\":{\"lat\":52.1905481,\"lng\":20.919931},\"html_instructions\":\"At<b>placArturaZawiszy</b>,takethe<b>1st</b>exitonto<b>Al.Jerozolimskie</b>/<wbr/><b>DW717</b><divstyle=\\\"font-size:0.9em\\\">ContinuetofollowAl.Jerozolimskie</div>\",\"maneuver\":\"roundabout-right\",\"polyline\":{\"points\":\"efw}Hkmb_CZhBZ~A\\\\~A\\\\|A\\\\~AVnAR|@bA~E`@nBVlAH`@VvAJd@TjAPr@`@tBFZDRz@vEj@xDTlAH`@VxAJh@`@~BBHDX~@zFr@pDb@bCf@lCn@jDV|AFXNj@BL@DLj@X|ATrALb@p@lBhB`H\\\\lAPn@`@vAv@jCd@`B^xAr@bD`ArEDDBBDJ`AnEBJl@lCh@|BR~@FRH\\\\?DXhA@D@F?BNl@?BT|@ZxAbAnE`@fBH^h@rBl@nBj@zAX~@rA`CpArB?@hH`MBDT^Zf@NXl@bAp@dA|ClF|AjCVb@\\\\j@?@R\\\\DHDHdBvC~CnF@BZf@`AbBlAxBp@jAxCfFvEhIPXNXd@v@JPLTLRNVNVzBtD|ApCh@~@vAvB`A|AHLn@dArCzElAtBVb@T\\\\?@hB|CXd@~ClFT`@BDdCvE`BvCLTpC`FNR`CjDdAnAjAzADFLPdBdDBDT`@r@jAlB`DTf@z@|AdDdGhDrGt@pApEjItBxD\"},\"start_location\":{\"lat\":52.2251451,\"lng\":20.9891823},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"1.0km\",\"value\":1049},\"duration\":{\"text\":\"1min\",\"value\":43},\"end_location\":{\"lat\":52.1816858,\"lng\":20.9153845},\"html_instructions\":\"Continueonto<b>DK7</b>(signsfor<b>Ursynów</b>)\",\"polyline\":{\"points\":\"}mp}Hq|t~B|AlCd@l@bBxBh@b@dAn@n@X`A\\\\j@PPDPBbARdBd@lCl@f@Ld@J~@T`AXrBf@~A`@RFxFvAF@~EpA\"},\"start_location\":{\"lat\":52.1905481,\"lng\":20.919931},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.4km\",\"value\":393},\"duration\":{\"text\":\"1min\",\"value\":18},\"end_location\":{\"lat\":52.1782694,\"lng\":20.913971},\"html_instructions\":\"Taketheexittoward<b>E67</b>/<wbr/><b>E77</b>/<wbr/><b>S2</b>/<wbr/><b>S8</b>\",\"maneuver\":\"ramp-right\",\"polyline\":{\"points\":\"qvn}Hc`t~BTRPHd@Nh@Nb@JnA^D@dBb@b@JrBx@d@NpBh@b@H\"},\"start_location\":{\"lat\":52.1816858,\"lng\":20.9153845},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"1.8km\",\"value\":1848},\"duration\":{\"text\":\"1min\",\"value\":81},\"end_location\":{\"lat\":52.1783294,\"lng\":20.8909779},\"html_instructions\":\"Keep<b>right</b>atthefork,followsignsfor<b>A2</b>/<wbr/><b>E30</b>/<wbr/><b>Poznań</b>/<wbr/><b>E77</b>/<wbr/><b>Gdańsk</b>/<wbr/><b>E67</b>/<wbr/><b>Białystok</b>/<wbr/><b>S7</b>/<wbr/><b>S8</b>\",\"maneuver\":\"fork-right\",\"polyline\":{\"points\":\"ean}Hiws~BNJPFRFVFXHVF\\\\HXHXFZH`@JRFLFJDTLLJ`@VNRp@z@FJXl@Rj@HT|@xDNr@PbAF^d@dDBTLpA?^@HBdA@`@Ah@IbBGz@a@xCWpBs@fFWlBOvAMjAgB|OE^EXGn@ANAv@w@pH]bD[jCg@hEc@~Cs@dFgAdHSpAUjAQbAKl@\"},\"start_location\":{\"lat\":52.1782694,\"lng\":20.913971},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"3.3km\",\"value\":3286},\"duration\":{\"text\":\"2mins\",\"value\":115},\"end_location\":{\"lat\":52.1960924,\"lng\":20.8531008},\"html_instructions\":\"Keep<b>left</b>andmergeonto<b>E67</b>/<wbr/><b>E77</b>/<wbr/><b>S2</b>/<wbr/><b>S8</b>\",\"maneuver\":\"keep-left\",\"polyline\":{\"points\":\"qan}Hsgo~Bg@jCe@dCc@rBg@~Bm@lCc@pBe@lBgAhEsAhFu@bCs@bCyAtEm@rBaAlDeArDADOf@Qf@EFEL_@jAmB`Gm@nBi@fB{AvEsA`EiBbGgBvFWv@ABm@hBaAvCaBhF{@lCaAdDGXIh@KZ[`AM`@M`@[`A[bAKZK^K\\\\]`A[bA_@bA]bA]bAAB[~@]bAAB[|@_@bA_@bA_@bA_@`Aa@bAa@~@Q`@GNINa@|@S^Q\\\\c@|@e@x@e@z@e@z@Q\\\\S\\\\cAbBa@p@OTo@~@q@|@q@|@W\\\\MNA@IJYZY\\\\s@z@eBvBYZW\\\\s@z@GFoB|B_AjA\"},\"start_location\":{\"lat\":52.1783294,\"lng\":20.8909779},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"1.6km\",\"value\":1628},\"duration\":{\"text\":\"1min\",\"value\":55},\"end_location\":{\"lat\":52.1983837,\"lng\":20.8324511},\"html_instructions\":\"Keep<b>left</b>tocontinueon<b>S2</b>\",\"maneuver\":\"keep-left\",\"polyline\":{\"points\":\"qpq}H{zg~ByBnCeApAs@`A_AtAq@fAaAhBYf@eAjCa@fAEJOb@Sj@w@tCg@bCS`AG\\\\YdBG^AJM`AKv@It@Gv@Gv@OnBEx@Cv@Ex@Cx@AhAAhA?bA?t@?P@dA@hABfADhAFfAFhAHbAHfAFj@BZJbALdANbAHj@DRb@|Bd@|Bf@xBf@vBf@zBj@tBThAHz@\"},\"start_location\":{\"lat\":52.1960924,\"lng\":20.8531008},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"89.4km\",\"value\":89361},\"duration\":{\"text\":\"48mins\",\"value\":2903},\"end_location\":{\"lat\":51.8946814,\"lng\":19.6588382},\"html_instructions\":\"<b>S2</b>turnsslightly<b>left</b>andbecomes<b>A2</b>\",\"polyline\":{\"points\":\"{~q}Hyyc~B|ClMzCpMr@xCbBjH~AlHp@tCb@rBHb@n@tCl@xClA|FjAnIr@zFh@tFXzD@PDv@?@NvBDn@LfB^rLDzBBfA?NBt@@p@FvDHfEJjFPdHZrHZnGBd@@b@\\\\`Fp@jJh@vGBVJhAFbAb@hGZ~Dp@nIBR@V@D@FDd@`AzM|@`LPxBl@xHB^D`@`@dEh@bFjAfHj@hC`AtDlBxFh@|A~@tBf@`Aj@`AbA`BnAdBbDhD~@z@|AjA`@Xx@f@vCbBxCxAlCvAhBbA`BdAnBtAZVfA~@DB^`@TRl@l@fBpB|ArBb@p@n@`APX\\\\l@h@`Ap@nAl@nAjAlCDJBF@B~@bC`ApClAnDZdAnBnGRt@vA`FlC`J~H~WdCtIf@bBdAvDnB~GdChJ|@hDf@lB|A~F`BzG\\\\zANl@|@xDnAlF~A~GDPnBlJJh@~A`I?D`BnIx@pEz@pE~BjN`BjK@FJl@j@xDn@dEXrBh@lDn@bFxAtKjAxJl@jFl@fFx@rH\\\\`DD`@xBtSj@rFr@bHNxAdBrPTjBTtBp@nGBTt@nHlBbRr@tGNxAzBrTjCrWDb@NtAD^TvBdDn\\\\TpCJ~@ZlDhB~SHv@N~A?BxBjYl@~HJjA?HH`A\\\\|DvArRvAxQnAzPNnBx@pKh@zG^~ElB~Vz@lMl@xJf@pId@zKHzABp@z@~Sl@hOd@rKr@~O\\\\dGb@hIv@nOn@hKZfF\\\\zEr@pK~@~KT|CBXJnA`@hG@Pd@zF|@rK`@nEf@vFDb@BXD^X~Cd@~EpBjR?BHr@jAzKtClVp@hFv@`G`AbI~B~PdEhZt@`FfDhUbIdi@tBxNfAlHh@pDhE|XvEf[hFj]vBxNFZzAdK\\\\zB`CbPnAzHzAjJbBzJzHt^XrAnBjIfClKj@`ClBhIx@fDHZH\\\\dBjHT|@lD~OdCpLhApFlCfN`BnI`@rB|BlM@FP~@|AfJjCdPzCbSBVLp@Jr@`@pCdFz_@xAzL^bD^bDpBhQ|Dd^H|@Jv@rCrXLhAfB~PvDf^Jv@?FRhBD`@P|A@LFt@RbBNjAHt@D\\\\@L\\\\xC|BvSTnBTpBv@vGz@lH|@jHdCvRZ|BrCzRf@jDlAtHfH~c@\\\\xBlAdHLt@Lt@fBjKpDfTNt@@HbH~c@BLHh@xBbOhCvQTxAj@pElEb^`AzIPdBv@hHx@~HfA`Lj@dGr@nHd@pFj@|G|@~KHv@v@rJBXFx@lBrWFt@XxDFv@BZBZdAlODn@@J@J~@zMt@dK`@`FV|C?JtAhPvBrT`AjIHv@Jv@J|@xA|KlEzXdDrQdAjF`EpQJd@d@zBxDtNp@fChAfE~CdKhAlDZdAr@tB~@pCL`@tBbGr@vBvJzY`BdFb@pAb@zAxFtRlC~JpEzQ@DH\\\\jCtLjEnUNt@Nr@|BzLNv@Jp@dCxO|@vG@BHr@lB`Nd@pD~BxR|BlW~@hNFz@h@bJDv@LrBz@hOj@vM\\\\xIj@`Tl@f`@@^?rAFnWClG?x@CdGKbMAZClAaAld@ErAQ|G?Bq@hW]xL[xKItBy@d\\\\a@fOWvI?BQbHOvFAh@i@hSg@pVOxIClBOhL?JApBEjCAhFATE`MAxF?X?bJHhTDjF@jBBjCBdCFvEP~JJdFFnDF~BT|IH|C`AzXH`CBl@xAjYnDre@`A|LDh@?@PnB@@z@pJvA`ObA`Kx@nHfArIpCfS~@pGdAtGx@`Fr@hEtAnIlHhf@b@dC`@bCpLds@DRxIdi@Jl@zBxNd@pCf@|ClCdQ`B|KHb@@FHl@XjBJt@F^dCxPz@rGJt@Jv@~AhLpDxWtBnQbClSb@fDZlCrJr}@LfAPlBtAjNxAdQDj@JdAFv@|AdQDf@pFxt@bAfNjCla@rAnUdBtYnA`Tl@jLDt@Br@j@zKDx@l@rKDv@Dx@bD|k@RjDtApVN~BFbAFbAhA~O@J@T`@zENpB\\\\xDv@zIZhD|@vINxATzBj@rF\\\\~CDXhDvYxAfKxAbK`@bC|A~JxC|PR|@P|@?@|@tEnAlGdCzLhArFz@dErApGXnADTFTn@pC|CxMLh@Nf@zGpYJd@Lf@lDnOtBlIdBnG`@rA`@vAb@vATv@jAvDhAdDp@jBJb@^dA@D@BFJNZLTbDbIP^N`@f@jAfCjGXn@Rf@zL~XhDjI?@\\\\x@Xt@NXfApChB`FbBzERj@n@nBDPr@tB|@xCdBxFpCdKjAjE^~AbDhN?@R|@~@tEPx@Ln@Nr@@BLr@lEdTNr@^fBVnAz@dElFnXPx@vBrJLf@BHdBdHJ`@h@rBRp@T|@nAnEdDpK~BxGpBnFFNLXTl@HPdAjCdFjLlApCdAxBr@zATd@b@|@pGzMh@jAbHpOtBdFlA`Dl@xADLlDrJrJpZzBhIdB`HrDxOd@tB`AbFNr@tAbHtCrOfD~QnFzXbD~QdDhQdJzf@~BbMdElSdAbFr@fDPt@dArEhAfEzAbGnBxGX|@bCrHpB~FRf@JXd@lAZ|@j@`BvBtFXp@Zv@rG~O`CfFxAxCJPhCvElQbZFJhCbEb_@jl@BBpDzF~@|AZf@r@jA?@pEdIxClFrDpHP`@tF~LvAdDZt@Zt@d@jAvDxI~J~V~DvJj@pAn@vAdAbChA|B~@jB~BtE`@v@bCfEh@~@b@t@vBnDpF`IV\\\\h@r@j@t@nAbBhD`EvEhFfClCdIxI\\\\^\\\\^xEfFjD|DbCxC~BzCRZRZHLxHhL^p@Xf@fB|CzBfExDvHLVr@~AVj@rCxGfDbI\\\\`ATl@rCfI@BhWdw@|HnUlAtDTn@|@nCr[paAhM~a@J^J^b@vAxBhI~@tDPr@~CfMdAjEb@bB`Hh\\\\Rz@j@tC~@pEp@`DrAnGZtANr@x@vDrAnGBJfB~HFP|DnQPp@~@fE~FhUR|@Tz@DPtEbPPl@?@nDpLrDzKzAtE`@nADLrA|DTl@lC~HnAhDtGbQTl@n@dBjA`DbGhPzCfInCtHvClItCnI|BzGxBbHtBbH|@~CPl@Pn@lB|GjBhHfBhHp@rCPv@Rt@Np@xAlG|AnH|AvHxA|HvAxHLx@Lt@t@lEnAbIdAhHhAdI^rCd@tDrAjLd@nEVtBp@zGt@xHl@bHl@dIv@hKHzBb@bJh@dKFrA?P^lI^hKn@rTRnJPhJDnD?x@RdQ@x@?x@@x@@x@HbHD`H?z@Bx@^vT@n@?HHzC?PBz@D~BNrEHxBVhGP~Cn@dKz@|Kh@dGn@xGn@|FXbC@LHp@Ff@\"},\"start_location\":{\"lat\":52.1983837,\"lng\":20.8324511},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"31.0km\",\"value\":31038},\"duration\":{\"text\":\"18mins\",\"value\":1084},\"end_location\":{\"lat\":51.6502732,\"lng\":19.5768664},\"html_instructions\":\"Taketheexitonto<b>A1</b>/<wbr/><b>E75</b>\",\"maneuver\":\"ramp-right\",\"polyline\":{\"points\":\"wtv{Hwr~vBCP?L@RN|AZxCNpAd@xEZpC^hDz@jIZbDLrA^tEVbDFjADx@L~BL`CNzCFnAHrAF~APzDLnCDdAN|DHjCHbCH~BHfCB~@@n@@p@@`@?b@Ab@A\\\\?BC\\\\C\\\\Eb@Gb@Kh@Kh@Mf@[|@[|@M^]fAM`@I\\\\Ib@G^E`@Ir@ChAArA@p@@f@T`BXzA^lAf@`Aj@t@`@b@XVLH^NRFLB?@N@L@J@P?R@TADAJATEPGPERIRKPMRONOBAJONSLQLSJWNWL_@L_@Jc@J_@Je@Lm@x@}DNu@Pq@Nk@FSFUTq@BKPe@Pe@Rc@n@yAvAwBnEaHb@q@`@q@DKBOPUXc@RY^e@Z_@n@u@^_@b@e@\\\\_@LODIFUlAeAhAaAjAgAfA_AjAeAh@e@^]z@u@RQ`@_@TSp@m@h@c@b@a@d@c@n@k@l@i@vAoAZW`A{@j@g@pBgBPQpBgBVUhEuDPQv@q@^[|BqB^]jB_Bl@e@^[^YVSf@a@x@q@DCzAiAd@]rByAlA{@^Un@c@p@c@FEx@i@@A^SZUj@]z@g@lAs@f@[n@]LIRKf@YpAq@l@[`@Uh@Wz@c@j@Wz@c@\\\\Q`@Qd@UhBw@rAk@vB{@pAg@j@Sf@S@?|@[DCd@OpAc@r@WjDeAtBk@d@MtA_@~@Uh@Ml@O|@S`AUpAW`ASd@IDAhB]l@KrAS~@Op@K`@GVEj@IrAO`AK~@K`AKHAt@GPCXCj@Ed@EXC~@GNAZCF?b@Ch@C`AE~@El@C`@A\\\\A`ACrAC@AjBCdA?\\\\?D?n@AtC@`A@z@@V?n@@`@@F?~@B`ABtCJlBJvCN@@`CP`CPtAPtANF@pAPbBTl@JvARtATh@Hj@LF@`@F@@~@Ph@Jj@LhB`@tAZhBb@h@L~@V~@V~@VxDlAhBl@rAd@@?`DjAnAd@XJFB~CpAnAh@`Ab@nAj@nAl@h@Vh@VPJ|@b@l@Zb@T^R^T`@T`@T\\\\RbBbAbBdAx@h@|@j@x@j@nAz@d@Zr@f@~BfBn@f@p@d@`@ZXR^XZT\\\\X^Zh@b@BBZV\\\\ZRP^XJH^\\\\jA`Af@b@t@r@t@l@XXLJd@b@jAjAv@t@BBLLNNf@f@d@b@t@v@f@h@^`@b@b@d@h@r@x@d@h@b@f@HHBDRVx@`A\\\\d@xAfB^`@d@l@RTLNNNDFNRr@|@RVp@~@tAlBRXl@z@r@dAPXLNd@t@NR\\\\h@tApBbArAPTPTLPb@j@`@j@tAjBr@~@tAfBp@~@d@l@\\\\d@X`@V^\\\\b@\\\\f@@@v@hAZb@tAnBV^Zb@tC~DDFZ`@Zb@x@fAPVp@~@dB`CZ`@z@jArAjBbAvAdAxA`@h@n@z@xApBX^Z`@r@~@`@f@t@|@DDz@`AVZDDn@p@JLZ\\\\bAdARTv@t@t@v@d@b@v@v@h@f@`@^d@`@b@^nAdAb@\\\\f@`@v@l@?@d@^z@p@x@l@j@`@hAx@bBhAz@h@PJz@h@fB`APJnAp@zBlAzBdAbCdA\\\\LVL`@PdAb@RHB?VLj@Tp@Th@RHBh@RXLVH~@^p@Tz@ZLFPF|@\\\\THPHTJRFTHd@RRFTHnAd@f@Pj@TTJd@Pf@Tb@Nj@Tx@\\\\dBv@pAj@h@ZbBz@RJJFB@|@d@^TFDjAp@h@ZRLLHv@f@XRz@h@p@d@@?^X@?@@^X\\\\TLJj@b@TPRNPNPNl@b@v@p@j@d@BBXVJHPPRNJJXVb@`@`B~AbBbBb@b@dAfAr@v@dAjAt@x@x@|@VXX^d@f@`@f@^^d@l@f@h@j@p@f@j@t@x@\\\\`@`@d@xA`BdAhAfAlATT^`@PPj@j@ZZh@h@`@^`@^RRl@h@p@l@v@p@^\\\\RNFFVRBDXTXRJJ`@ZPNVRf@^b@\\\\ZT\\\\VXR`@Xd@\\\\XPb@ZPJTNf@\\\\d@Xf@ZRLVNb@VRLTLPJTLTL`@RDBd@Vf@XTJRJHDNHPH@?f@Th@XTJRJf@RB@b@RVJh@TRHLDTJJDNDb@PbA^l@R^Nf@PB@l@PnA`@p@P`@Lh@Nd@Jp@P`AVj@LlAVZFd@Jj@Jh@Hh@Jj@Jh@Hj@Hh@Hj@Fj@HRBx@HF@TBVBT@XDh@Db@DD?pAHj@Dj@Bh@Bj@Bj@Bh@@j@@T@TAZBb@?j@@`A?fA?x@Ap@Ax@AxBGzBIxAKrAIz@Ij@E`AMr@IfAM|@MZEh@Gr@K\\\\G^IZG`@IXGpAYvCo@hBg@fBe@LEdA[r@Mh@ObC{@rAg@nC_Ad@Mz@Wt@UjAa@|FkBjFmBhBi@`Bo@vDiAzEcBnC}@JE`DeAd@QvIwCBAfJyCf@Sf@SnGuBnGsBfX}I`AWtCs@|DeAxBc@dB[tBa@bC[j@Kx@KLA|Fo@hDOzBMfGG~GLhADjGPfJ~@tGdAvBb@dDr@|C~@`D`ATHF@D@HBLDn@RpK`ErAh@`I`D|NdGd@RrCjAhEdBhEdBf@RRJpAh@FDHBXJhDvAb@N`@P|@^fHtCpJ~DhGhCZND@vF~BhGfClDxAvEjBjGjCdGbClH|CnAh@`@N`@PhCfA`@PxH`DtGlCvAf@dKdErB|@bAb@zD~AdDvAjCfAh@RzKpEfHtCfHpClBx@vB|@vEpB|GpCzB|@|B`Az@^`@PlBt@zB~@pBx@nBx@n@VhCdA`@PpBz@x@ZbC~@dAf@fA^lAf@x@\\\\bBr@lChAjCfAhCdAnChAfCfAj@R\"},\"start_location\":{\"lat\":51.8946814,\"lng\":19.6588382},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"3.5km\",\"value\":3542},\"duration\":{\"text\":\"2mins\",\"value\":134},\"end_location\":{\"lat\":51.6433566,\"lng\":19.5365982},\"html_instructions\":\"Taketheexitonto<b>S8</b>\",\"maneuver\":\"ramp-right\",\"polyline\":{\"points\":\"e}fzHmrnvBNPRN`Af@\\\\JTJD@x@^|BbAdBr@fAb@nChAzAn@vD~AFB~Al@?@@?NH^T\\\\VXTd@^HFZRZP`@Pj@VXL`@Nl@\\\\f@Vj@XTNRJVPLHPLBBNJJHDDPPRPRTNRHJFHNVBBPXT^P\\\\NVJVVj@JVPh@Vt@^rAZ~APlARlBHdAD|@Bz@@|@?v@?`@Af@CbAE|@K`B?@UvEGbAkBj]M|B?FEzA@p@i@pJe@hJg@bJk@rKUlEg@jJObD[pFE`AIdA]nGCV]vFI|@It@?BGj@q@bGOdBMrBKdBAb@QvE\"},\"start_location\":{\"lat\":51.6502732,\"lng\":19.5768664},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"201km\",\"value\":200752},\"duration\":{\"text\":\"1hour48mins\",\"value\":6485},\"end_location\":{\"lat\":51.1753445,\"lng\":17.0855647},\"html_instructions\":\"Keep<b>left</b>tostayon<b>S8</b>\",\"maneuver\":\"keep-left\",\"polyline\":{\"points\":\"_rezHwvfvBB|AKvFKvCUxF?NOxDWbHI`GGjG@dQP|JHlEZ|HJzB\\\\~FBd@Dl@XnDNrBNbBZbDVbC\\\\dDR`BJt@Hn@Hn@BRh@`Ed@vCJl@Jr@`@~BX`Bb@|BZzAZ~AX|AZ`BPx@\\\\`Bv@zCDR`@xA^tAl@xBh@nB`@xAr@rCf@`Cd@|BZbBJh@Ln@ZbBHd@R`APdAP`ALx@PdAP`AT~A\\\\fCLdANfAFf@RfBJ`ARbBPdBPjBNfBNlBNfBLhBFhALfBNpCTjDz@rNn@tKXrEXnE`@pGVvDNfCB`@LrBNhCTrDT~DJdBJdBLjBPnC`@vGLjBXrE?DDx@LrBLfBLxB@VFbABRDx@@^HpADz@RrEDdAHrBJnCHpCFfBDnBFnCDjBDlBBjB?DBdBDxEBlB@hB?jB@jB?lB?hA?`@?jA?jA?ZAhA?fAAlBCnBCtCGvEElCAd@EjBIxDCdAEhACdA?JEx@ElAGnBAb@IfBClAMpECdACnBClCAjAAfCAfB?pC?`A@bCBhC@dBDlCDdBApAHdCHxCDv@HhCJzALdCVzEBVBf@Fr@`@nFBZB\\\\BZl@hHNfBh@pHDt@?@RdCDj@Dp@|AdS|@pLhAdN?HV|CNnBDd@Dr@Dt@ZbE|@hLDl@D^BXNjBv@jH?FNhAPxAXxB\\\\zC`AdGVvAl@jDp@fDrAtGH\\\\HZtB`K`@jBNr@fAbFjBrIzB|J`BvGXfAnApEhAfEtAnEv@bCxAfEr@tBv@nBTl@xAxDBDPh@rB`FpBpErBpEvEpKbBjExDtJrBjGpBjGbB|F`@tAhBrGlApE\\\\dAfAtEn@nC`@dB`@dBpAjG@Dv@lEbAhEZjBLp@xAxI`@dCp@vEzAxKt@fGr@lGl@jGj@~F^nE@JVpDZfEZhFR|CRtDXrGRvENfEJfDFrBPzIV~KZhOPfI@l@?JZbOZdODvBHlDBnAV`LFlCDjCXlM`@jR^nQ@X@^P|HHfEJnDNlDRpEThER|CTvC\\\\dE^lE^rDh@nE`@fDFf@p@xE~@bGbA~FdAlFbA`Fz@rDx@bDv@`DhArE|@|Dz@|Dh@fCf@jCVnA`@vBv@xEz@hFHr@Jh@Jv@^tC`@`D^jDDT\\\\hDVfC\\\\hEX`EFt@Fv@R`DJnB|@zMRfDRfDBb@j@vJ^nGThDZrF`@tGT`ENhC\\\\nFf@tIVhEb@zGH`BFz@b@tHdB~Yp@|KFv@bAxPJnBNzBt@lMd@lILpBn@lKLpBJpB`@rGbAzPnAzR^jEX`Df@fFr@jGn@jFXrBVbBXtBNhAPfA`BrK`DrQP~@t@xDjAtF^pBPx@Rx@hAlFpCbL@BNr@b@bBPp@Pr@Np@r@tCxBxHfHxT`HbRxEjLbD`IVj@Tj@JVfJjThChGVj@Tl@Vj@jAnCdKlV`@bADHfD`ITf@Tf@dF`Mj@nAhApC\\\\v@nFjMVl@bD`IxApDrAlDZ`An@lB|@rCt@xBhA`EzAxFp@dCLh@Nj@lA`Fl@nChAdFt@vDrAjHX~A`@`C`@`CjAfIr@dFPvANhANfA@LrCnWF^RnBHv@Ht@^hDbAbJz@nHHv@\\\\zC`AfIhBtPTlBTpBfC~T`DvYP~ATlBHv@t@tG`AnHfBfMLt@Lt@x@bFfBlKpCzNvBlKLp@Nr@Nr@Nr@~BhL`AnFzA~I`@nCJv@NbAd@xCVfB^tC`@|DhBpPhAjPHnAD~@BTj@vJLlCLxCBv@Bp@DpADpABzAFtCDxA@hADzBBzC@vB@xA@dFEnKG`GMnIGxBOrFUhFI|BGlAOfDk@tJg@jHo@pHa@bEAHGl@AHI|@eDbZqArKyA~Mo@hF?BIt@_AbIyBlRq@lGG^CTIv@s@vGIr@iAbKKx@Kx@SpB_@fD[dD[fDE^_@vEYjE?BUdDAPOvCGhAOhDK|CAf@ANCx@IhCInDIjDGlEElE?LAjI?@?lD?vABrFFbFFbEDlBBhAJvCRbF\\\\lH\\\\tFb@|HX`HNfEHdDHrEBxBBhEAlFAxFGlE?TG|DOrFSjEMpCGfAUxDg@jHm@pGkA|Kg@tDg@fDq@dEeD|QcEhUGZqDxRw@jEqBrK_BfJmA`H{@bFeAnGq@|Dq@fEId@wAlJq@vEm@fEsAvJu@lFs@xEs@fFs@bFq@xECPq@bF_AxGcBtMcAvHm@hE}ClSoB`No@jEgBzLa@jC_A|Gw@zG[lDs@xHc@pFM`CQzB[|FCXYjGYzG[lIiAfYUfGk@vMg@bNo@pNS~CKdBYvEuAjO{@|Gy@nGyAdK[tBy@xEw@pDOp@iAvFgAxEQr@_@xAk@rBi@rByBtHeCxHiA|CUl@e@nAyCnHgBbE_BjDqCxFiBdDi@`AmAxBsAxBCFoClEQVyG|Kc@v@[d@iElHyDvG{CbGkA|BEH}CrHsCjHiBlF{@tC}@zCm@xBw@`Dq@pCq@jCq@bDc@tB}@bFSvAUvAaAnHk@vEUhBE`@_BvO_@lF]|FQzDEx@?JUnHKrHGdH?bHFvHJxHDdABx@RhF`@lI`@~GFx@?JNbBl@fHdAhKdB|Qx@bIvBxSLlAJlA`AxJbAbKRnBLnArBrSnAfM?DL|@hA|LVdChBvPn@`GNrAHt@Jv@n@pEvDfTj@bCLn@Ll@bAhEvBjIv@lCpDvKt@nCnCvHr@zAp@|AZp@~BtEfBlDxBvDn@fABF`GdJh@v@zBvClDdEjBdClBdCrMnP\\\\`@Z`@\\\\`@rCrDtF`Ix@jApEzG|@vAn@hAn@hA`A`BhE~HtEnJVj@dAbCtB`F|GlQfBhFj@dBn@lBVv@jBfGxBbI|AzGv@fDPp@Nr@Pp@t@|CtCvNtAdIj@jDRjAXxAl@lE`@vCtA|Kx@xHD`@BTFv@n@fHh@|GZjEJfBLdB`@lKl@bRBx@Z`KPnHL~Gb@pNDhA|@rUj@bOt@zMj@vINrB\\\\lChDvXb@lCh@`Dj@~CHh@b@xBTnAZrAdB|H?Dz@zDZxAnApEh@lBf@lBtB~F^bAPf@Vt@FPpAhDBFVj@L^nCxG`@|@BDTj@dA~Bv@`B@BNZDHBBHRBFjBnDhDbG~AhCfBlCBBl@x@Zb@Z`@Zb@zApBhD`EZ^@@tBxBbAbAbE|DxBjBp@f@@@^Xx@n@tCrBjDxBzA|@r@^l@ZRL^Rp@^fBv@dEdB|Ad@lBh@jD`AzA`@rAZb@J^HbB`@xDf@b@FLBT@hBPxDZnEZF?ZBzDZ~F^zALH?`@D`RlAb@DjBLjDThAHlN|@hAFhAHb@BjCP@?fAFB?fOjA|CT^DB?tHl@tAN`I`At@Lv@JvANrEv@fF~@fFfA~Cv@lB`@z@T`Cj@pHvBtC|@tCjAfCdA`EdBvItDlD|AtAn@lAl@nBbAPJfAf@z@h@zBnAdCxAtEtC`CzA`@X^Vp@b@dFnDpGxExEpDzChClDrCjDnChB~At@l@xC|BlDfCTP`M`KhElDjCzB^ZHFrFnEf@b@VPnB~AxDzCrAdAbBrAhE`DpCzB^X^ZRNlFpE~ExEdFbFzA|A\\\\^JJPR|BbCvBhCjD~DrA`Bn@t@|AlBpBdCbBxBvBdCtD~EhCzC\\\\`@\\\\`@h@l@jDrDrB|Bz@~@@@\\\\XzCjCp@j@lA`A|BjB^X^XbBlAtCpBj@\\\\hAr@rBpAvBlARJ`@RbAd@DBZNdB|@dBv@^N@@jDtAhAb@xFfBTFdAZnBh@vEbAlF|@JBzFt@zALdE\\\\xAFb@@T@L@t@BbADvC@vB@h@?\\\\@b@?b@?nC?rD?|@@H?vEAT?bE@xAAnD?|E@f@?nC?fA?|A?tAAfA?R?zB@b@?`@?d@?fA?lJ?dB?fA@t@?P@zBDdEPbAD`Gn@pEp@b@Fb@FNBRDzFjA\\\\JbL~CZJ\\\\JhCfApCnAvAn@x@^HDbAf@`@RFBpDrBzCnBTN`C~A^X~B~AxEzDbBzANNpBjBt@t@^\\\\j@j@l@p@pAtAFF|ExFLNZb@hB~BvCzDRV\\\\h@|DlGxBtDxB|DhCjFXj@zDlIvCdH`BbEj@zA|@dCL^L`@fCvH^rA|AxFzBtIr@zCtCfNNr@Nr@r@lDhApGVbBdAdHlBrMx@lH@BBr@FdALhAZzCHx@r@hHz@xId@|Ex@|H@Nj@tFRbCPjBBV?^jCrWhGvm@hGrm@xA|JhB~LhBtKPt@Nr@dD~N@DLj@z@fD~@rD@B~ArFjAzDhElMDLTl@xDdK`@bATj@vCnGzAtC`@v@vApClBtDt@rA~@dBJPXf@fAfBvBhD~AbCjBlC~B|CZ`@BDt@|@nChDJLdF`GDFFF@@jF~F\\\\`@\\\\^\\\\^d@h@tD`EPR\\\\^dAhAhDvDpAvAfJbKBF\\\\`@v@dAjAxApC|CpDbEtAfB|AnBf@n@VZ\\\\`@PRJJvC`D`ErELNDDDDrBdCbAnAfAlA|AhBd@h@r@z@\\\\`@p@v@jAzAnA|AfArAj@t@d@t@~@pABFx@hAt@fAt@lAp@bA|A`C`AzA~@zA|@zA|@zA~@|Aj@bAjBbDrAdCrBpDT`@xChFzl@reAx@zA~DrHzDrI~F|MrHjSZbATl@rAbExDhMbBjGdCpJfBtHdAbFF\\\\tBlKf@lC`@bCt@rELt@nAzHLx@Jt@b@xCL|@b@~CJv@j@nEv@fHhApLJhAnAfOvApPDf@@LZdDPlBd@hFxA~Pb@fFHt@`@zEZjDN~An@pG\\\\rCXhCnA~Iz@nFr@bE`B`IrBrI~AxFb@|A|AzEb@vAt@xBrArD|AxD`BtDlAfCtBfER`@xAjCrCtE`GnJjCxE~CdGxBxE~DpJ^`AvClIdBpFfBnGt@nCJ^T`APp@ZrAd@rBVjAhAxFhAxGfAfH~@dH?Dp@bGj@bGd@~Fl@hKZrGRfHBjBBvALhGDrFB`IIbI[`OsA~^Cz@Cv@sA|^?DCp@AD{Bbo@iCfu@SpFMhDUjGKjCg@dO_AdWKxBCr@e@jMYbIM~COvDOlCEh@U~DEn@Gx@KhBKfAQvBSbCMfAGr@CVIp@_@nDaBpMm@~DW~A]xBEVKp@oAlIkBxLqAnIWfBgBxLK|@e@`Dg@rES|BKbAGp@?FOvBOtB[zFUpFI`EGxDCnCAbC@tF?Z?X?tB@`CFdFNjGPlFPlERvDB^@Vv@zKdBbU^bFNnBFv@^~E^~ENnBFv@Fx@Fv@B\\\\lApOFv@L~Az@hLL~A^~EFv@NnBFv@^~Er@pJhAlOFv@F~@vAhRbApNH~@nBfW@F^vEd@xGL~Ah@fHRfCL~A`AnMfAfOp@jJl@hI?FFn@HnAvB|Wr@dIt@nHJ|@Ht@RlBP~Av@jG@Ln@bFl@tENfA|@~G|@~G@B`C`QlBrNNfArA|Jb@tCD`@bCpQn@nEz@rG\\\\xBfB|MJn@f@jDx@zFvArKf@lDLfAPjA^nC|@vGzBfQp@fFXbC~@lIl@~GPnBNpBB\\\\JxAB\\\\h@vI@JXlGBXRrFDz@PvHB~AFnDFvH@~BAhJEvGKhHM`GIhCa@xKq@~LEh@c@dGk@`HYlCi@pEi@xEc@vDm@lEiAtHc@dCa@`COr@}AtHc@bCiAfFiBhH]fAUz@o@~Bq@~BgAbDk@hB_@fA]bAiBdFsAlDSb@Qb@gAnCg@hAm@rAc@dAuBpE]n@o@fAYf@Yf@qAdCm@hA]j@qA|BgBrC_CzDsErHu@hAyIjNkBvCsCpEsBfDyAzBoArBgC~DqMxSiCdEuCnEe@t@g@v@m@bAaIpM}BvDgAlB_AdBgAvBiBpDgB|Dq@bBcAbC]|@g@tAUj@kC|HgBhGiAbE}ArG{A|GeAtF]vBiBrLcA~Im@bGg@pG[|EU`E]~HUfHE`DIbGCzG?hA?jAA`BBrBBdBFpCBrA@tADfADnBJfCFvABV@h@FnAFzAhA~ODp@JdAXvCHz@Hn@Jz@LhAh@|DXpBXjBTrAPfAj@xCNz@XzAF`@`@dB^|AVtA`A`Ed@lBhArEzDlMPl@rD`LlBdGzAtE|CrJv@xBzA`FxD~K~CrJvAdEn@nB~BbH~G~SvEnNfApDTr@bArD`@xAx@bDxAbGvB~Jh@pCl@|Cl@lD^hCf@fDb@hDpApKHr@Hv@J~@l@`Gp@fINhBJ|APvCRpDJ~BN~CJzBL`CRxDHlBJjCBx@Dt@Dr@Dl@Bp@NbDTbFRxEHpBV`EBnATzENzCPdEPpERjEFfA^nIJfCLzBJzBJ~BB\\\\HtBNtDBb@BVLpCBj@HvAL`DZtHLtCFdBBh@d@dKDt@RjFRjCLlCPdEJdCJzBB`@FpABjADxBFxCHpCLvJ?d@?d@?rC@rCCnDAtBAnAAdB?|B?|BMtG[fMIbDEbBCv@KjDCx@MbFE~@MnESxFCp@AFo@vQAZAh@g@|ME~@C~@UjHIbCAf@Cv@KpEMnFIdFA`AAx@GnG?N?pBAx@?x@?vB@nEB|DFvEJrFFhDFrB^hK\\\\nGd@rGHlAJ|AxAtQv@vIr@tH|BfW|@vJHz@v@pIlAdNjA`NhBfRjBnThBhSLpAd@tFt@bIhA`Mt@hHXnCZlCt@lGz@tGdBpKp@xDTlAFXNr@ZvAnA|FvA`GdDbMvErP|EpPbGdTfB|Fb@zAdBhGnCrJVfAFXFXx@fEp@nD~@tFfAnIt@zHP~BT~CD~@HrBXjH@h@JxI?hDCtECbEKxGC`B?NCpACxAGpCOtIIfC?@ABCp@?@?DIrBKtG?DAx@EjBAn@[hXArECfG@zCB|DF|EBtBH|CPvFLnDXlGThF`@zGXbET~C`@hEBRHv@p@bG|@pHr@~FTlBHt@D^DVf@hEf@tEl@rGBVb@~EDXB\\\\RlBFl@`@nFLhBDx@Dv@@HRvEPrE@PJdDHrCD~C@hCFzE?zD@dAJpKN|PBd@@f@PtO?@?x@BxDFpG?N@zC@x@?`AFnG@~@BxCD|DDrE@lD@N?hDChFEtBGtBM~DMrDOxCOzBKnACd@S`COnBABm@pFE\\\\c@`DKp@?B]rBW`BEV{@lEaApEcA|DcAtDkA|D{@tCOd@oAhEoA`EwBlHmAjESn@Ql@gAfEq@zCMj@_@nB[zAy@rEa@jCE^_@pCMfAc@lDa@`FEj@YvDOhCGfAEbAKfCIdC?DE~BEzBCnC?D?jB?rEFlEFfELhEHxAH~AZ|ELzAFt@JrALrAHt@Hx@Ht@TjBHv@@DdAjHn@nDZhBrA`HZhBx@dF?DjAtIb@jD`@dF^tE`AxNxGb`Ar@xIHz@h@hGd@rEd@jEf@fETfBz@xHl@lFnAvKHv@RlBJv@hCjUTlBnBvPDb@TlBR~ARhBR~An@`GZjDXdDLrBJrBDhABd@@RHjBLbI@|B@jT?xF?|@A`F?lA?d@@xU?xD?tB?x@AvCE~IWbLMxE]vOW`KKjD?PY`MUvLIrDCx@Cx@c@nQUtLATAb@i@fVe@hVCbAGrCSbHInG]|MGrEGbCI|BOnDCd@M|B]~GOpCGv@KnBANo@rHu@rH[pCsA|LeAdJu@hHK~@eBxOA@Iv@SlBi@~E}@zJ_BlN}AdNMdAKt@Iv@Kt@SfBeAfJc@bEIv@}@hI}@lIyB~R}@pIq@~GUlBu@vGmBnQ_AjJe@lEu@bHCRE`@Iv@e@|Ei@lFa@zEK`BIbAUvEOrEGzAItDIlGAd@?x@CzB?bD?d@BpDFnFJ`FXbHLxDr@dLZ`D\\\\vDd@nEr@pFv@dFNdAd@lCLr@RdAx@|DzAvGfAnErAtEvCbJv@nBTl@|@|BlC`Gt@|AtArC`@t@~@`BbBvClC~DxC~DtBlCxChDjCdC\\\\\\\\j@h@PNjEjDpH|FjBtArBbB~BhB|FtEd@^xAjA~BjB~CdCdGjGd@h@j@t@hC`DzBvCj@x@xAzBzBvDPXjCnENVvDvGjGvKhDzFNVlAvBn@hAdChEd@v@lEtH|AjCz@pApBxCRVt@bAz@fA|AjBv@~@bBfBtBtBr@n@xElE\\\\X~EfEzFdFXT|HrGvF`FzClCh@b@jEdE`A~@bCfC|BjCfB~BbBvB`B|BX^^f@`FbI`AbB`AdBz@fB|@lBd@`Ab@|@xBfFdBrEr@pBp@jB~AzERl@HVzArEdCfIhCfIfCnIfCbIH\\\\Rn@Rn@`BhFrC~Il@nBb@xAb@zArAfEtEdO|@rCPj@~B|H`CfIt@|B|C|JlCzIt@dCvBnHlDfLJ\\\\jDvKFPnG~SHZrChJz@rCxAjE|CzJJ\\\\nCbJTt@tBtGRl@Rn@Xz@vCpJHVjBfGf@|APn@@@rCjJxD|LRn@HXtAvE^nAtA`GpAlFDX\\\\fBf@bCjAlGf@dDz@jFt@~EZfCz@`H\\\\pCn@fFl@~E~AlNxE|a@xAbMLfBFt@HlAZlFPhDRdHDbG?^@`DB`A?T?nCCdEClCCr@Ap@M|DSlEU~D]vEe@rFc@|DIf@UvBMv@m@`Em@rDu@|D_BjH}@vD}AzG{@vDWtAUtA]`C[jC}@bIC\\\\EZCPoCfWIt@eBfPK~@K~@MjAIv@SfBqB`RSnBSjBmBhQ[dD]dDS`COpBCRCb@MbBQlCUlFEtAElAIlDEhDAfC?hD@rDDhCFjDFdBH`CJhCFfADv@TlDRfC^bEr@fHv@tHHt@Jv@hBxPjBhQ|@hIHv@|@hIHt@|@jIDZlBlQnB|QJt@z@`I\\\\xDJt@Ht@ZfCr@rFR|B~@pJhCxVx@rHHt@Jv@Ht@~@tIxHbr@t@nILpATlBzAlNJt@\\\\dDzCpXv@fHp@~Hd@xFXdEFv@PbCXdFVzFB~@Dx@ZxIXvJF|DH`EBfP?rB?dBOzXQfJc@nJ[zGEv@Ch@iBt[Ex@Ev@ShDGv@{@pOgDjj@Gv@MnBEx@Gv@y@~M}ApXGv@Ev@mAdTMpB]xFw@bNoAbTm@jKMnBw@|Me@xHwBp^gApRKpBMnBu@tMa@bHIlAGnA{@xPEt@SfDEn@E`AQ|FQdFIfEAP?f@ArBCfDAvP@pFHnFJbEBv@NxFVfG?Hv@~Nn@|JDv@Fv@RhD`@pGx@bOPhDFdApAtSLpBp@lK|AjWnAzSDfAv@dOFdAB~@|@tWH|CHjFD`G@|BLh_@?|GF~OBxID`ND|MBvFFdR?`L?bBAhJ?`@NbMLbEVtL@r@l@lQBt@h@xJRnDTfDTtCXfD^fE`@~Db@dEb@vDf@tDt@vFd@|C^jCl@nD\\\\lB\\\\hB`@rBTjA\\\\fBd@|B\\\\~Af@xB\\\\xAp@vCZnAp@fCf@jBd@`Bh@jB^pAf@~An@rBl@hBp@tBp@pBf@vA|@bCf@pAL\\\\v@nBv@jBt@hB~@vBhAbCr@|A~@pBz@~A`@x@h@v@`CjElBhDhBvChBnChAbBl@z@t@bAzCbEfBvBbBtBlCxCbChC|BzBpClCdB|AhB~AbCzBpAfA^Z|DlDtBhBfB~AhBzAdCzBdBxAdCzB`ElDfDtCjD|ChDvCtCdCtChCfCvBbB|A|ArARPhA`A\\\\Z^\\\\JHnAfA`Az@tAlAdA~@zBnBrCbCvCjClC~BpBdBtBjBzBvB`B~AvA~ApAzAvAjBJLv@bAbAxAp@dAn@`AfAlBjArBdArBv@zAFNN\\\\N\\\\R^fD~Hj@dBl@dBfDzJlCfIDJ~CzJRl@?@z@lCjB~FlDhKjBrF|@dCTp@jBbF\"},\"start_location\":{\"lat\":51.6433566,\"lng\":19.5365982},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"4.4km\",\"value\":4434},\"duration\":{\"text\":\"3mins\",\"value\":152},\"end_location\":{\"lat\":51.16763839999999,\"lng\":17.025739},\"html_instructions\":\"Continueonto<b>A8</b>\",\"polyline\":{\"points\":\"{djwHw_hgBfBnElEzKhEbKt@hBP^xEjLhBrE|@lClBnG~BfJz@hEf@lCj@dDLp@VnBj@hEJx@@JJ`ADZLxAZnDd@hHXlFNjEFzCDnBBfI?jAAfA@`B?v@?dAItMAxE?x@AvGCbOAdC?f@CnH?bBAfG?vBIrWA`A?l@AtA@lEAdAA|@@f@IvPGjT@z@@x@A~@?hDCpJApCA~E?jAAjA?Z\"},\"start_location\":{\"lat\":51.1753445,\"lng\":17.0855647},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.9km\",\"value\":924},\"duration\":{\"text\":\"1min\",\"value\":55},\"end_location\":{\"lat\":51.1666417,\"lng\":17.0177832},\"html_instructions\":\"Attheinterchange<b>WrocławPółnoc</b>,keep<b>right</b>andfollowsignsfor<b>DK5</b>/<wbr/><b>E261</b>/<wbr/><b>Poznań</b>/<wbr/><b>Centrum</b>/<wbr/><b>Wrocław</b>/<wbr/><b>Poświętne</b>/<wbr/><b>Polanowice</b>\",\"maneuver\":\"ramp-right\",\"polyline\":{\"points\":\"wthwH{i|fBGh@?NAd@At@?p@AjA?rAC|LAvB?R?`AAf@GdAC\\\\ETEZSx@[v@Wd@_@b@[XW\\\\IHMXIVIb@EXAV?`@@^DZLh@NZPTVRRF`@@\\\\E`@E`@Mj@QPIPMXSXW`@e@RWLWTQ\"},\"start_location\":{\"lat\":51.16763839999999,\"lng\":17.025739},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.4km\",\"value\":354},\"duration\":{\"text\":\"1min\",\"value\":18},\"end_location\":{\"lat\":51.1646243,\"lng\":17.0216724},\"html_instructions\":\"Continueonto<b>E261</b>/<wbr/><b>S5</b>\",\"polyline\":{\"points\":\"onhwHcxzfBN[HO`EmIzAwC`@cARq@Lk@Lm@Hg@\"},\"start_location\":{\"lat\":51.1666417,\"lng\":17.0177832},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.3km\",\"value\":318},\"duration\":{\"text\":\"1min\",\"value\":20},\"end_location\":{\"lat\":51.1651253,\"lng\":17.0260119},\"html_instructions\":\"Takethe<b>DK5</b>exittoward<b>Wrocław</b>/<wbr/><b>Poświetne</b>/<wbr/><b>Polanowice</b>/<wbr/><b>Psary</b>\",\"maneuver\":\"ramp-right\",\"polyline\":{\"points\":\"{ahwHmp{fBFM@E@GBUBYB}@?o@?c@Ac@?CA[IeAOaBEg@MgAKs@EWQgAUwAQ}@\"},\"start_location\":{\"lat\":51.1646243,\"lng\":17.0216724},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.2km\",\"value\":202},\"duration\":{\"text\":\"1min\",\"value\":16},\"end_location\":{\"lat\":51.1640027,\"lng\":17.0276529},\"html_instructions\":\"Slight<b>right</b>\",\"maneuver\":\"turn-slight-right\",\"polyline\":{\"points\":\"aehwHqk|fB@e@AI?W?Q?W@MBQBSDSBODMFOFOJQNQNORKPITGL@N?TBH?HAFA\"},\"start_location\":{\"lat\":51.1651253,\"lng\":17.0260119},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.8km\",\"value\":776},\"duration\":{\"text\":\"1min\",\"value\":70},\"end_location\":{\"lat\":51.157097,\"lng\":17.0283859},\"html_instructions\":\"Continuestraightonto<b>Żmigrodzka</b>/<wbr/><b>DK5</b>\",\"maneuver\":\"straight\",\"polyline\":{\"points\":\"_~gwHyu|fBR@dAL~@DZ@`AHbAHl@D`@@dEChBMlBSzBYXCr@KXCZA^Cl@ATCLG\\\\O|@_@f@SNIb@KNE\"},\"start_location\":{\"lat\":51.1640027,\"lng\":17.0276529},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"5.7km\",\"value\":5740},\"duration\":{\"text\":\"6mins\",\"value\":384},\"end_location\":{\"lat\":51.1270757,\"lng\":16.9796216},\"html_instructions\":\"Turn<b>right</b>onto<b>Nowaka-Jeziorańskiego</b>/<wbr/><b>DK5</b><divstyle=\\\"font-size:0.9em\\\">ContinuetofollowDK5</div>\",\"maneuver\":\"turn-right\",\"polyline\":{\"points\":\"{rfwHmz|fBBDDBBBFBBBDDDD@?DHDHLVEtAGrAItBKnEAdAKrECrB?`AADCnCAZUbFKnBUfDCb@Q|BSrCOdBKbBYbGCz@CjACjA?lB?@@nBBfABfA@ZBn@@`@Bh@JxADj@Bb@Fh@Dj@D\\\\@D?BNlAL`A@HJt@Lt@Hd@X|AThAXfAZlADNRt@t@xBDJN`@`@|@`@p@V`@JLFHn@t@DD\\\\\\\\XVVRTN^Rh@V`@PTH\\\\H\\\\FXBv@F\\\\@x@?jACnBEXAZAfFKhFKtBEb@AxHO~@?`A@|@FxANzARtDl@l@JH@H@fHdAjLdB|@Nv@Nn@Nx@XtAj@d@XZRj@d@jAnAr@x@d@n@d@v@d@v@Vh@Zj@bAlCf@`Bd@`Bp@lBL`@FRfAfDDJdAbDrAjEj@dBTv@JXHV`@pAtE~NRp@Rh@`@rAL\\\\@Bl@jBj@lBPf@J\\\\?@Nd@Ld@Lj@Nx@Jn@Fh@Db@?HBVBd@Bx@B|@BhABhBB^?LDbADd@Bd@DXF^H^BLJ\\\\FNFN?BTf@HNT\\\\V\\\\bA`AXXDD@@@BLLPLDDb@\\\\d@b@BB`DnC\\\\ZVRLLtC`CNLn@f@LNVZ\"},\"start_location\":{\"lat\":51.157097,\"lng\":17.0283859},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"3.6km\",\"value\":3551},\"duration\":{\"text\":\"7mins\",\"value\":393},\"end_location\":{\"lat\":51.11167760000001,\"lng\":17.0211535},\"html_instructions\":\"Turn<b>left</b>onto<b>Legnicka</b>\",\"maneuver\":\"turn-left\",\"polyline\":{\"points\":\"gw`wHsisfBNLFD\\\\ZBKJ[FSj@yB^_Bx@wCrAeE~A_FPi@Ty@Pu@Jg@ToATaAT_AV}@^oAf@_BbBwFj@oBh@kB~@{CxAsE`@{AJYf@{ADKpAiEhAuDBIVq@Ri@d@oAx@mBt@aBFMBE@CXi@f@_A^m@R]^m@l@y@f@q@PSd@q@b@a@HIh@k@\\\\_@FGJKHK@ANO`@a@zB{Bl@k@TQx@y@^c@PWb@k@\\\\m@hAqBZg@z@iBXa@DIDKDIVe@DIh@iABIb@cATk@d@yAl@sBRs@@?Pc@Pc@Nc@Pe@BMJc@R_BDgBDkCHoFB}CDeC@aAH_E?]@U?Q?Q?oA?i@?yA?{@?{ABwB?YDuCBoADoADaA?GFoABY@YF{@NiBB[RaBTkBN}@BQDQD[n@gDBKBM@K\"},\"start_location\":{\"lat\":51.1270757,\"lng\":16.9796216},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"0.4km\",\"value\":434},\"duration\":{\"text\":\"2mins\",\"value\":92},\"end_location\":{\"lat\":51.1079315,\"lng\":17.0214455},\"html_instructions\":\"Turn<b>right</b>onto<b>Podwale</b>\",\"maneuver\":\"turn-right\",\"polyline\":{\"points\":\"_w}vHem{fBr@w@JSF?H?H?`@GFAHCPEFEj@MDCTELEJCJAVEl@EVC\\\\CPALALAJ@D?L@ZDtCb@XNLNb@RLD\"},\"start_location\":{\"lat\":51.11167760000001,\"lng\":17.0211535},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"1.4km\",\"value\":1373},\"duration\":{\"text\":\"5mins\",\"value\":295},\"end_location\":{\"lat\":51.1002865,\"lng\":17.0339479},\"html_instructions\":\"Continueonto<b>Piłsudskiego</b>\",\"polyline\":{\"points\":\"q_}vHao{fBFBD@BBD?D@F@H@R?nDCH?jBAJ?`@?pBANCVINGPIdA}@BADEBADE@AFGDEPMLKRQZ_@RY`@s@JWJSFMh@iAbA_CrAuCj@}ANg@L_@BMDMDS^oB`@wBJo@BOH[ZuAPu@Nu@H_@PkA@E@G@G@U@U@G?I@GFa@Ho@l@qFJo@f@_Ep@yFFc@BS@O\"},\"start_location\":{\"lat\":51.1079315,\"lng\":17.0214455},\"travel_mode\":\"DRIVING\"},{\"distance\":{\"text\":\"44m\",\"value\":44},\"duration\":{\"text\":\"1min\",\"value\":10},\"end_location\":{\"lat\":51.0999106,\"lng\":17.0337644},\"html_instructions\":\"Turn<b>right</b>onto<b>Stawowa</b><divstyle=\\\"font-size:0.9em\\\">Destinationwillbeontheright</div>\",\"maneuver\":\"turn-right\",\"polyline\":{\"points\":\"yo{vHe}}fBHBHBv@\\\\\"},\"start_location\":{\"lat\":51.1002865,\"lng\":17.0339479},\"travel_mode\":\"DRIVING\"}],\"traffic_speed_entry\":[],\"via_waypoint\":[]}],\"overview_polyline\":{\"points\":\"yax}Ho{f_CObHhJjv@vq@jaE|Z~oAt|@x~Ady@`tAvh@z~@l_@bLtn@xQpInZkHhu@we@hbC_h@|`BwZ|m@{Yb_@gLfa@nE~cA|^vdBzEp|@fW|lD`On]|YhS|Zb[zfAfxDlt@bxFlp@f~Hp`@bkGh{@hhGvs@b}Dvh@zpCfe@zbEvr@nbF`s@~tGv^j~Ctp@z{Bbk@jjCbWncEuN~`IbAztBfVhsDtwAznJfi@fcGrUxdExq@dbFrv@nmCvj@d|AtVxiA~`@v|All@xrAhjA`kF~c@lgBxxAjkCpy@~hB~dBfcC~bAhoCpgAb}DrlAfhErt@d~Bv\\\\|pBnSbpFxTjuCf@rmAhA|[xG~AtNo]vj@aq@riBknAty@yPzk@_AxjAvQf|@xd@leAhjA|tAxcBrzA|s@vgAheAns@rb@te@tJdt@{@pyAkc@p~Aee@~d@aBpp@vJf_A~^f{@~]pkDjwAtqBt}@`GxKhAld@cS`yDfAxlB`Jxq@xX`uArYd|F}Bd`BnMlbChc@v{CpkAhqDhU~fBfH~cCdFjxBfTl~An^`~Cdb@~pG~WrvApw@hxB`p@n}Ar\\\\pnA~l@hbFzf@hzD{@nwAwS~vBmSrpCtAdyDucAlxGaXvzBoTfkDc`@dsAss@rpAe^`tAmGnmBhm@`bG~c@xmA~aA|sAzq@`iB`TrrAhNleDjVt}Alj@vlAzj@|f@nj@rP~jCzRtpAh^|w@xe@n_CnnBbuAfvA|d@~Qbr@dGvcBb@nv@xRth@``@tm@b`Ahb@t{Apy@n}Gve@`nAva@pk@~nAjwAfkBjzCny@fiCvRzdBrQtcBln@b_Bxf@nvAhMb{BwZjdIe[jcDpz@vmLrr@|nFvCruA}O~qBcw@puBylA~mBif@xqAcNxqBfDrx@v{@feDxh@hsBfJ~mAlKf`CzDp{CqGfpCvp@hdI|o@|cCtKpxAYb_DxStfCt@rhDse@j{BoHvz@|Bx|@~m@lcGrJlcA[t|BiO|tFwo@tcGuGpeAjIn}Ax_@nkAd_Ap~@hcB`aCjgArcAne@v_AjyAxzEv`A`nDnU`fCgExdAuh@f_Ey@v_AjQtiBlu@tgHv@bjCcQlxC}ZxzGzXnwHlDxbDrOnrAfk@vdBju@~_AxaAn{@xoAjsAjuAr~DhHvfAYzxA}@jyCgFzOpI~BpO}[gAyTL_L`Je@ze@_CaEr~AdNzm@neArFrj@`TbQfh@tQ~|@bY~[zd@gyAx\\\\sf@pLcTbGse@`Do~@tGeIvL@vXuAbRkl@`GaZ\"},\"summary\":\"S8\",\"warnings\":[],\"waypoint_order\":[]}],\"status\":\"OK\"}"

}