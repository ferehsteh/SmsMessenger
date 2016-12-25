/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2012-2015 Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lb7.alish.smsmessenger.sample.provider.message;

// @formatter:off

import android.net.Uri;
import android.provider.BaseColumns;

import lb7.alish.smsmessenger.sample.provider.SampleProvider;
import lb7.alish.smsmessenger.sample.provider.base.AbstractSelection;

/**
 * Columns for the {@code message} table.
 */
@SuppressWarnings("unused")
public class MessageColumns implements BaseColumns {
    public static final String TABLE_NAME = "message";
    public static final Uri CONTENT_URI = Uri.parse(SampleProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * number of contact
     */
    public static final String NUMBER = "number";

    /**
     * body of Message
     */
    public static final String MESSAGE_TEXT = "message_text";

    public static final String DATE = "date";

    public static final String TYPE = "type";

    public static final String STATUS = "status";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." + DATE + AbstractSelection.DESC;

    public static final String[] ALL_COLUMNS = new String[]{
            _ID,
            NUMBER,
            MESSAGE_TEXT,
            DATE,
            TYPE,
            STATUS
    };

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NUMBER) || c.contains("." + NUMBER)) return true;
            if (c.equals(MESSAGE_TEXT) || c.contains("." + MESSAGE_TEXT)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(STATUS) || c.contains("." + STATUS)) return true;
        }
        return false;
    }

}
