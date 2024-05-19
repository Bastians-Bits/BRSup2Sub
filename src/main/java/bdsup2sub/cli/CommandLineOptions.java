/*
 * Copyright 2014 Miklos Juhasz (mjuhasz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bdsup2sub.cli;

import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.List;

public class CommandLineOptions {

    static final String HELP = "h";
    static final String VERSION = "V";
    static final String OUTPUT_FILE = "o";
    static final String VERBOSE = "v";
    static final String LOAD_SETTINGS = "L";

    static final String RESOLUTION = "r";
    static final String TARGET_FRAMERATE = "T";
    static final String CONVERT_FRAMERATE = "C";
    static final String DELAY = "d";
    static final String SCALING_FILTER = "f";
    static final String PALETTE_MODE = "p";
    static final String MIN_DISPLAY_TIME = "m";
    static final String MAX_TIME_DIFF = "x";
    static final String MOVE_IN = "I";
    static final String MOVE_OUT = "O";
    static final String MOVE_X = "X";
    static final String CROP_LINES = "c";
    static final String ALPHA_CROP_THRESHOLD = "a";
    static final String SCALE = "S";
    static final String EXPORT_PALETTE = "P";
    static final String EXPORT_FORCED_SUBTITLES_ONLY = "D";
    static final String FORCED_FLAG = "F";
    static final String SWAP_CR_CB = "w";
    static final String FIX_INVISIBLE_FRAMES = "i";

    static final String ALPHA_THRESHOLD = "A";
    static final String LUM_LOW_MED_THRESHOLD = "M";
    static final String LUM_MED_HIGH_THRESHOLD = "H";
    static final String LANGUAGE_CODE = "l";
    static final String PALETTE_FILE = "t";

    static final List<String> OPTION_ORDER = Arrays.asList(HELP, LOAD_SETTINGS, RESOLUTION, TARGET_FRAMERATE,
            CONVERT_FRAMERATE, DELAY, SCALING_FILTER, PALETTE_MODE, MIN_DISPLAY_TIME, MAX_TIME_DIFF, MOVE_IN, MOVE_OUT,
            MOVE_X, CROP_LINES, ALPHA_CROP_THRESHOLD, SCALE, EXPORT_PALETTE, EXPORT_FORCED_SUBTITLES_ONLY, FORCED_FLAG,
            SWAP_CR_CB, FIX_INVISIBLE_FRAMES, ALPHA_THRESHOLD, LUM_LOW_MED_THRESHOLD, LUM_MED_HIGH_THRESHOLD,
            LANGUAGE_CODE, PALETTE_FILE, OUTPUT_FILE, VERBOSE, VERSION);

    private final Options options = new Options();

    public CommandLineOptions() {
        createAndAddOptions();
    }

    public Options getOptions() {
        return options;
    }

    private void createAndAddOptions() {
        Option help = Option
                .builder(HELP)
                .longOpt("help")
                .desc("Show usage information and exit.")
                .build();
        options.addOption(help);

        Option version = Option
                .builder(VERSION)
                .longOpt("version")
                .desc("Show version information and exit.")
                .build();
        options.addOption(version);

        Option output = Option
                .builder(OUTPUT_FILE)
                .longOpt("output")
                .desc("Specify output file.")
                .hasArg()
                .argName("filename")
                .build();
        options.addOption(output);

        Option verbose = Option
                .builder(VERBOSE)
                .longOpt("verbose")
                .desc("Verbose console output mode.")
                .build();
        options.addOption(verbose);

        Option loadSettings = Option
                .builder(LOAD_SETTINGS)
                .longOpt("load-settings")
                .desc("Load settings stored in configuration file even if running in command-line mode.")
                .build();
        options.addOption(loadSettings);

        Option resolution = Option
                .builder(RESOLUTION)
                .longOpt("resolution")
                .desc("Set resolution to: keep, ntsc=480, pal=576, 720p=720, 1080p=1080, 1440x1080\nDefault: keep")
                .hasArg()
                .argName("resolution")
                .build();
        options.addOption(resolution);

        OptionGroup framerateGroup = new OptionGroup();
        {
                Option targetFrameRate = Option
                        .builder(TARGET_FRAMERATE)
                        .longOpt("fps-target")
                        .desc("Synchronize target frame rate to <fps>.\nPredefined values: 24p=23.976, pal or 25p=25, ntsc or 30p=29.967, keep (preserves the source fps for BD&XML, else default)\nDefault: automatic (dumb!).")
                        .hasArg()
                        .argName("fps")
                        .build();
                framerateGroup.addOption(targetFrameRate);

                Option convertFrameRate = Option
                        .builder(CONVERT_FRAMERATE)
                        .longOpt("convert-fps")
                        .desc("Convert frame rate from src to trg\nSupported values: 24p=23.976, 25p=25, 30p=29.970\nauto,trg detects source frame rate.")
                        .numberOfArgs(2)
                        .argName("src,trg")
                        .valueSeparator(',')
                        .build();
                framerateGroup.addOption(convertFrameRate);
        }
        options.addOptionGroup(framerateGroup);

        Option delay = Option
                .builder(DELAY)
                .longOpt("delay")
                .desc("Set delay in ms\nDefault: 0.0")
                .hasArg()
                .argName("delay")
                .build();
        options.addOption(delay);

        Option filter = Option
                .builder(SCALING_FILTER)
                .longOpt("filter")
                .desc("Set the filter to use for scaling.\nSupported values: bilinear, triangle, bicubic, bell, b-spline, hermite, lanczos3, mitchell\nDefault: bilinear")
                .hasArg()
                .argName("filter")
                .build();
        options.addOption(filter);

        Option paletteMode = Option
                .builder(PALETTE_MODE)
                .longOpt("palette-mode")
                .desc("Set palette mode.\nSupported values: keep, create, dither\nDefault: create")
                .hasArg()
                .argName("mode")
                .build();
        options.addOption(paletteMode);

        Option minDisplayTime = Option
                .builder(MIN_DISPLAY_TIME)
                .longOpt("minimum-time")
                .desc("Set minimum display time in ms.\nDefault: 500")
                .hasArg()
                .argName("time")
                .build();
        options.addOption(minDisplayTime);

        Option maxTimeDiff = Option
                .builder(MAX_TIME_DIFF)
                .longOpt("merge-time")
                .desc("Set maximum time difference for merging subtitles in ms.\nDefault: 200")
                .hasArg()
                .argName("time")
                .build();
        options.addOption(maxTimeDiff);

        OptionGroup moveGroup = new OptionGroup();
        {
                Option moveIn = Option
                        .builder(MOVE_IN)
                        .longOpt("move-in")
                        .desc("Move captions inside screen ratio <ratio>, +/- offset <offset>")
                        .numberOfArgs(2)
                        .argName("ratio,offset")
                        .valueSeparator(',')
                        .build();
                moveGroup.addOption(moveIn);
        
                Option moveOut = Option
                        .builder(MOVE_OUT)
                        .longOpt("move-out")
                        .desc("Move captions outside screen ratio <ratio>, +/- offset <offset>")
                        .numberOfArgs(2)
                        .argName("ratio,offset")
                        .valueSeparator(',')
                        .build();
                moveGroup.addOption(moveOut);
        }
        options.addOptionGroup(moveGroup);

        Option moveX = Option
                .builder(MOVE_X)
                .longOpt("move-x")
                .desc("Move captions horizontally from specified position. <pos> may be left, right, center\n+/- optional offset <offset> (only if moving left or right)")
                .optionalArg(true)
                .numberOfArgs(2)
                .argName("pos[, offset]")
                .valueSeparator(',')
                .build();
        options.addOption(moveX);

        Option cropLines = Option
                .builder(CROP_LINES)
                .longOpt("crop-y")
                .desc("Crop the upper/lower n lines.\nDefault: 0")
                .hasArg()
                .argName("n")
                .build();
        options.addOption(cropLines);

        Option alphaCropThreshold = Option
                .builder(ALPHA_CROP_THRESHOLD)
                .longOpt("alpha-crop")
                .desc("Set the alpha cropping threshold.\nDefault: 10")
                .hasArg()
                .argName("n")
                .build();
        options.addOption(alphaCropThreshold);

        Option scale = Option
                .builder(SCALE)
                .longOpt("scale")
                .desc("Scale captions horizontally and vertically.\nDefault: 1.0,1.0")
                .numberOfArgs(2)
                .argName("x,y")
                .valueSeparator(',')
                .build();
        options.addOption(scale);

        Option exportPalette = Option
                .builder(EXPORT_PALETTE)
                .longOpt("export-palette")
                .desc("Export target palette in PGCEdit format.")
                .build();
        options.addOption(exportPalette);

        Option exportForcedSubtitlesOnly = Option
                .builder(EXPORT_FORCED_SUBTITLES_ONLY)
                .longOpt("forced-only")
                .desc("Export only forced subtitles (when converting from BD-SUP).")
                .build();
        options.addOption(exportForcedSubtitlesOnly);

        Option setForcedFlag = Option
                .builder(FORCED_FLAG)
                .longOpt("force-all")
                .desc("Set or clear the forced flag for all subtitles.\nSupported values: set, clear")
                .hasArg()
                .argName("state")
                .build();
        options.addOption(setForcedFlag);

        Option swapCrCb = Option
                .builder(SWAP_CR_CB)
                .longOpt("swap")
                .desc("Swap Cr/Cb components when loading a BD/HD-DVD sup file.")
                .build();
        options.addOption(swapCrCb);

        Option fixInvisibleFrames = Option
                .builder(FIX_INVISIBLE_FRAMES)
                .longOpt("fix-invisible")
                .desc("Fix zero alpha frame palette for SUB/IDX and SUP/IFO.")
                .build();
        options.addOption(fixInvisibleFrames);

        Option alphaThreshold = Option
                .builder(ALPHA_THRESHOLD)
                .longOpt("alpha-threshold")
                .desc("Set alpha threshold 0..255 for SUB/IDX conversion.\nDefault: 80")
                .hasArg()
                .argName("n")
                .build();
        options.addOption(alphaThreshold);

        Option luminanceLowMedThreshold = Option
                .builder(LUM_LOW_MED_THRESHOLD)
                .longOpt("lum-low-med-threshold")
                .desc("Set luminance lo/med threshold 0..255 for SUB/IDX conversion.\nDefault: auto")
                .hasArg()
                .argName("n")
                .build();
        options.addOption(luminanceLowMedThreshold);

        Option luminanceMedHighThreshold = Option
                .builder(LUM_MED_HIGH_THRESHOLD)
                .longOpt("lum-med-high-threshold")
                .desc("Set luminance med/hi threshold 0..255 for SUB/IDX conversion.\nDefault: auto")
                .hasArg()
                .argName("n")
                .build();
        options.addOption(luminanceMedHighThreshold);

        Option languageCode = Option
                .builder(LANGUAGE_CODE)
                .longOpt("language")
                .desc("Set language for SUB/IDX export.\nDefault: en")
                .hasArg()
                .argName("language")
                .build();
        options.addOption(languageCode);

        Option paletteFile = Option
                .builder(PALETTE_FILE)
                .longOpt("palette-file")
                .desc("Load palette file for SUB/IDX conversion. Overrides default palette.")
                .hasArg()
                .argName("file")
                .build();
        options.addOption(paletteFile);
    }
}
