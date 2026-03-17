package com.smartguard.app.database;

import androidx.annotation.NonNull;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteStatement;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation", "removal"})
public final class IncidentDao_Impl implements IncidentDao {
  private final RoomDatabase __db;

  private final EntityInsertAdapter<Incident> __insertAdapterOfIncident;

  private final EntityDeleteOrUpdateAdapter<Incident> __deleteAdapterOfIncident;

  public IncidentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertAdapterOfIncident = new EntityInsertAdapter<Incident>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `incidents` (`id`,`timestamp`,`eventType`,`location`,`imageUri`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Incident entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTimestamp());
        if (entity.getEventType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindText(3, entity.getEventType());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(4);
        } else {
          statement.bindText(4, entity.getLocation());
        }
        if (entity.getImageUri() == null) {
          statement.bindNull(5);
        } else {
          statement.bindText(5, entity.getImageUri());
        }
      }
    };
    this.__deleteAdapterOfIncident = new EntityDeleteOrUpdateAdapter<Incident>() {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `incidents` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SQLiteStatement statement,
          @NonNull final Incident entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Incident incident, final Continuation<? super Unit> $completion) {
    if (incident == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __insertAdapterOfIncident.insert(_connection, incident);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Object delete(final Incident incident, final Continuation<? super Unit> $completion) {
    if (incident == null) throw new NullPointerException();
    return DBUtil.performSuspending(__db, false, true, (_connection) -> {
      __deleteAdapterOfIncident.handle(_connection, incident);
      return Unit.INSTANCE;
    }, $completion);
  }

  @Override
  public Flow<List<Incident>> getAllIncidents() {
    final String _sql = "SELECT * FROM incidents ORDER BY timestamp DESC";
    return FlowUtil.createFlow(__db, false, new String[] {"incidents"}, (_connection) -> {
      final SQLiteStatement _stmt = _connection.prepare(_sql);
      try {
        final int _columnIndexOfId = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "id");
        final int _columnIndexOfTimestamp = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "timestamp");
        final int _columnIndexOfEventType = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "eventType");
        final int _columnIndexOfLocation = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "location");
        final int _columnIndexOfImageUri = SQLiteStatementUtil.getColumnIndexOrThrow(_stmt, "imageUri");
        final List<Incident> _result = new ArrayList<Incident>();
        while (_stmt.step()) {
          final Incident _item;
          final long _tmpId;
          _tmpId = _stmt.getLong(_columnIndexOfId);
          final long _tmpTimestamp;
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp);
          final String _tmpEventType;
          if (_stmt.isNull(_columnIndexOfEventType)) {
            _tmpEventType = null;
          } else {
            _tmpEventType = _stmt.getText(_columnIndexOfEventType);
          }
          final String _tmpLocation;
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null;
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation);
          }
          final String _tmpImageUri;
          if (_stmt.isNull(_columnIndexOfImageUri)) {
            _tmpImageUri = null;
          } else {
            _tmpImageUri = _stmt.getText(_columnIndexOfImageUri);
          }
          _item = new Incident(_tmpId,_tmpTimestamp,_tmpEventType,_tmpLocation,_tmpImageUri);
          _result.add(_item);
        }
        return _result;
      } finally {
        _stmt.close();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
