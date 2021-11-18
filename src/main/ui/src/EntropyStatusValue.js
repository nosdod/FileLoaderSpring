import * as React from 'react';
import Typography from '@mui/material/Typography';
import Chip from '@mui/material/Chip';
import Box from '@mui/material/Box';
import Title from './Title';

function getStatus(status) {
  if ( status === "CRITICAL" )
    return "error"
  else if ( status === "WARNING" )
    return "warning"
  else
    return "success"
}

function getStatusText(status) {
  if ( status === "CRITICAL" )
    return "Level Critical"
  else if ( status === "WARNING" )
    return "Level Warning"
  else
    return "Level OK"
}

export default function EntropyStatusValue({size,status}) {
  return (
    <Box
      component="form"
      sx={{
        '& .MuiTextField-root': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <Title>Entropy Status</Title>
      <Typography variant="h4">
        <Chip label={size} color={getStatus(status)}/> bytes
      </Typography>
      <Typography color="text.secondary" sx={{ flex: 1 }}>
        Status {getStatusText(status)}
      </Typography>
    </Box>
  );
}
